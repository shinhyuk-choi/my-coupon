package com.example.api

import com.example.application.domain.CouponInfo


data class CouponInfoResponse(
    val id: Long,
    val displayName: String,
    val expiredAt: String,
) {
    companion object {
        fun from(couponInfo: CouponInfo): CouponInfoResponse {
            return CouponInfoResponse(
                id = couponInfo.coupon.id!!,
                displayName = couponInfo.couponEvent.displayName,
                expiredAt = couponInfo.couponEvent.expiredAt.toString(),
            )
        }
    }
}

