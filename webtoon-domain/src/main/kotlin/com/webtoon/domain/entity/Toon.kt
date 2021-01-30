package com.webtoon.domain.entity

import com.webtoon.domain.entity.enum.ToonProvider
import javax.persistence.*

@Entity
@Table(name = "toon")
class Toon(

        @Id
        @Column(name = "toon_code", nullable = false)
        val toonCode: String,

        @Column(name = "serialize_day", nullable = false)
        var serializeDay: Int,

        @Column(name = "site_link", nullable = false)
        var siteLink: String,

        @Column(name = "img_src", nullable = false)
        var imgSrc: String,

        @Column(name = "toon_name", nullable = false)
        var toonName: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "toon_provider", nullable = false)
        val toonProvider: ToonProvider

) : AbstractBaseAuditEntity()
