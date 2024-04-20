package com.example.application.adapter.kafka

data class CouponIssueRequestMessage(
    val couponEventId: Long,
    val userId: Long
){
    companion object {
        fun from(couponEventId: Long, userId: Long): CouponIssueRequestMessage {
            return CouponIssueRequestMessage(couponEventId, userId)
        }
    }
}
