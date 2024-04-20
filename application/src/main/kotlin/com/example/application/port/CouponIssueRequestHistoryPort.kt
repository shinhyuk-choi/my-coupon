package com.example.application.port

interface CouponIssueRequestHistoryPort {

    fun setIfNotExists(couponEventId: Long, userId: Long): Boolean

    fun getRequestSequentialNumber(couponEventId: Long): Long
}