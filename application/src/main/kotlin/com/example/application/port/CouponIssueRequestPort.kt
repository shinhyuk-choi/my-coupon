package com.example.application.port

interface CouponIssueRequestPort {

    fun sendMessage(couponEventId: Long, userId: Long)
}