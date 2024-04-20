package com.example.application

import com.example.application.domain.CouponInfo
import com.example.application.port.CouponPort

import org.springframework.stereotype.Service

@Service
class UsableCouponListService(
    private val couponPort: CouponPort
) {
    fun listByUserId(userId: Long): List<CouponInfo> {
        val couponInfos = couponPort.listByUserId(userId)
        return couponInfos.filter { it.canBeUsed() }
    }
}