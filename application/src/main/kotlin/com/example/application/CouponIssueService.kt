package com.example.application


import com.example.application.domain.Coupon
import com.example.application.port.CouponPort
import org.springframework.stereotype.Service

@Service
class CouponIssueService(
    private val couponPort: CouponPort
)  {
    fun save(couponEventId: Long, userId: Long): Coupon {
        Coupon.generate(couponEventId, userId).let {
            return couponPort.save(it)
        }
    }
}