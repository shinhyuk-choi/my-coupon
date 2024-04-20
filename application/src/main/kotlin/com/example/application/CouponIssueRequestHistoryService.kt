package com.example.application


import com.example.application.domain.CouponEvent
import com.example.application.port.CouponEventCachePort
import com.example.application.port.CouponEventPort
import com.example.application.port.CouponIssueRequestHistoryPort
import org.springframework.stereotype.Service

@Service
class CouponIssueRequestHistoryService(
    private val couponEventCachePort: CouponEventCachePort,
    private val couponIssueRequestHistoryPort: CouponIssueRequestHistoryPort,
    private val couponEventPort: CouponEventPort,
)  {

    fun isFirstRequest(couponEventId: Long, userId: Long): Boolean {
        return couponIssueRequestHistoryPort.setIfNotExists(couponEventId, userId)
    }

    fun hasRemainingCoupon(couponEventId: Long): Boolean {
        val couponEvent = getCouponEvent(couponEventId)
        return couponIssueRequestHistoryPort.getRequestSequentialNumber(couponEventId) <= couponEvent.issueLimit
    }

    private fun getCouponEvent(couponEventId: Long): CouponEvent {
        val cachedCouponEvent = couponEventCachePort.get(couponEventId)
        if (cachedCouponEvent != null) {
            return cachedCouponEvent
        }
        val couponEvent = couponEventPort.findById(couponEventId) ?: throw IllegalArgumentException(
            "CouponEvent not found"
        )
        couponEventCachePort.set(couponEvent)
        return couponEvent

    }
}