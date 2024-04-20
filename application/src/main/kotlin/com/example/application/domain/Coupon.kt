package com.example.application.domain


import java.time.LocalDateTime

data class Coupon(
    val id: Long?,
    val couponEventId: Long,
    val userId: Long,
    var usedAt: LocalDateTime?,
    val issuedAt: LocalDateTime,
) {
    fun use(): Coupon {
        if (usedAt != null) {
            throw IllegalStateException("This coupon has already been used.")
        }
        usedAt = LocalDateTime.now()
        return this
    }

    companion object {
        fun generate(couponEventId: Long, userId: Long): Coupon {
            return Coupon(
                id = null,
                couponEventId = couponEventId,
                userId = userId,
                usedAt = null,
                issuedAt = LocalDateTime.now()
            )
        }
    }
}

