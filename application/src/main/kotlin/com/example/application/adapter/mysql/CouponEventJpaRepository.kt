package com.example.application.adapter.mysql

import org.springframework.data.jpa.repository.JpaRepository

interface CouponEventJpaRepository : JpaRepository<CouponEventEntity, Long> {
}