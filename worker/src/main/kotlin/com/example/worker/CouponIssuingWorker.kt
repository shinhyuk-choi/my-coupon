package com.example.worker

import com.example.application.CouponIssueService
import com.example.application.adapter.kafka.CouponIssueRequestMessage
import com.example.application.adapter.kafka.Topic.Companion.COUPON_ISSUE_REQUEST
import com.example.common.CustomObjectMapper

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CouponIssuingWorker(
    private val couponIssueService: CouponIssueService
) {

    private val objectMapper = CustomObjectMapper()

    @KafkaListener(
        topics = [COUPON_ISSUE_REQUEST],
        groupId = "coupon-issuing-worker",
        concurrency = "3"
    )
    fun listen(message: ConsumerRecord<String, String>) {
        val request = objectMapper.readValue(message.value(), CouponIssueRequestMessage::class.java)
        couponIssueService.save(request.couponEventId, request.userId)
    }
}