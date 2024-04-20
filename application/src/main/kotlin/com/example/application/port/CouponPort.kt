package com.example.application.port

import com.example.application.domain.Coupon
import com.example.application.domain.CouponInfo

interface CouponPort {

    fun save(coupon: Coupon): Coupon

    fun listByUserId(userId: Long): List<CouponInfo>
}