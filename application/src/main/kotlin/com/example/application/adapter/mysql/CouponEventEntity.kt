package com.example.application.adapter.mysql

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "coupon_event")
class CouponEventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val displayName: String,
    val expiredAt: LocalDateTime,
    val issueLimit: Long,

)