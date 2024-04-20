package com.example.application.adapter.mysql

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "coupon")
class CouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val userId: Long,
    @Column(name = "coupon_event_id")
    val couponEventId: Long,
    val issuedAt: LocalDateTime,
    var usedAt: LocalDateTime?,

){
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_event_id", nullable = false, insertable = false, updatable = false)
    val couponEvent: CouponEventEntity? = null
}