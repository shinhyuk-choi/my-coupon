package com.example.application.adapter.kafka


import com.example.application.adapter.kafka.Topic.Companion.COUPON_ISSUE_REQUEST
import com.example.application.port.CouponIssueRequestPort
import com.example.common.CustomObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CouponIssueRequestAdapter(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) : CouponIssueRequestPort {
    private val objectMapper: CustomObjectMapper = CustomObjectMapper()
    override fun sendMessage(couponEventId: Long, userId: Long) {
        val message = CouponIssueRequestMessage.from(couponEventId, userId)
        message.let {
            kafkaTemplate.send(
                COUPON_ISSUE_REQUEST,
                message.userId.toString(),
                objectMapper.writeValueAsString(it)
            ).get()
        }
    }
}