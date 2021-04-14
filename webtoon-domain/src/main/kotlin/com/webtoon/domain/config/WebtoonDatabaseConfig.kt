package com.webtoon.domain.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jmx.export.MBeanExporter
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.webtoon.domain.repository"],
    entityManagerFactoryRef = "webtoonEntityManagerFactory",
    transactionManagerRef = "webtoonTransactionManager")
@PropertySource("classpath:properties/database/webtoon-database-\${spring.profiles.active}.properties")
class WebtoonDatabaseConfig(private val mbeanExporter: MBeanExporter) {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "webtoon.jpa")
    fun webtoonJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun webtoonHibernateSettings(): HibernateSettings {
        return HibernateSettings()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "webtoon")
    fun webtoonHikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    @Primary
    fun webtoonDataSource(): DataSource {
        val dataSource = HikariDataSource(webtoonHikariConfig())
        mbeanExporter.addExcludedBean("webtoonDataSource")
        return dataSource
    }

    @Bean
    @Primary
    fun webtoonEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(webtoonDataSource())
            .packages("com.webtoon.domain.entity")
            .persistenceUnit("webtoonPersistenceUnit")
            .properties(getVendorProperties(webtoonDataSource()))
            .build()
    }

    private fun getVendorProperties(dataSource: DataSource): Map<String, String> {
        return webtoonJpaProperties().properties
    }

    @Bean(name = ["webtoonJdbcTemplate"])
    fun webtoonJdbcTemplate(@Qualifier("webtoonDataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    fun webtoonTransactionManager(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(webtoonEntityManagerFactory(builder).getObject()!!)
    }

    @Bean
    @Primary
    fun webtoonTransactionTemplate(builder: EntityManagerFactoryBuilder): TransactionTemplate {
        return TransactionTemplate(webtoonTransactionManager(builder))
    }

}