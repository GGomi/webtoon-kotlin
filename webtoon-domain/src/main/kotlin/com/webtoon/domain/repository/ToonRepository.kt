package com.webtoon.domain.repository

import com.webtoon.domain.entity.Toon
import org.springframework.data.jpa.repository.JpaRepository

interface ToonRepository: JpaRepository<Toon, String> {
}