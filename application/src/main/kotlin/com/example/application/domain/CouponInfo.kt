package com.example.application.domain

data class CouponInfo(
    val coupon: Coupon,
    val couponEvent: CouponEvent,
) {
    fun canBeUsed(): Boolean {
        return !couponEvent.isExpired() && coupon.usedAt == null
    }
    companion object {
        fun generate(coupon: Coupon, couponEvent: CouponEvent): CouponInfo {
            return CouponInfo(
                coupon = coupon,
                couponEvent = couponEvent
            )
        }
    }
}

