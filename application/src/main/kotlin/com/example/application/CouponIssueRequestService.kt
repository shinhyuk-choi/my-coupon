package com.example.application

import com.example.application.port.CouponIssueRequestPort

import org.springframework.stereotype.Service

@Service
class CouponIssueRequestService(
    private val couponIssueRequestPort: CouponIssueRequestPort
) {

    fun queue(couponEventId: Long, userId: Long) {
        couponIssueRequestPort.sendMessage(couponEventId, userId)
    }
}