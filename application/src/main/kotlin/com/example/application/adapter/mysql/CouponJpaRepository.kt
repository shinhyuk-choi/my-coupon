package com.example.application.adapter.mysql

import org.springframework.data.jpa.repository.JpaRepository

interface CouponJpaRepository : JpaRepository<CouponEntity, Long> {

    fun findAllByUserId(userId: Long): List<CouponEntity>
}