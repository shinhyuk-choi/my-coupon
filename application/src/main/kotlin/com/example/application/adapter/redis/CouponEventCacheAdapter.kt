package com.example.application.adapter.redis

import com.example.application.domain.CouponEvent
import com.example.application.port.CouponEventCachePort
import com.example.common.CustomObjectMapper


import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CouponEventCacheAdapter(
    private val redisTemplate: RedisTemplate<String, String>
) : CouponEventCachePort {
    private val objectMapper: CustomObjectMapper = CustomObjectMapper()
    companion object {
        private const val COUPON_EVENT_KEY_PREFIX = "coupon.event.v1:"
        private const val EXPIRE_SECONDS = 60 * 60 * 24 * 7L // 1 week
    }

    override fun set(couponEvent: CouponEvent) {
        val jsonString = objectMapper.writeValueAsString(couponEvent)
        jsonString.let {
            redisTemplate.opsForValue().set(
                generateCouponEventKey(couponEvent.id), it,
                Duration.ofSeconds(EXPIRE_SECONDS)
            )
        }
    }

    override fun get(couponEventId: Long): CouponEvent? {
        return redisTemplate.opsForValue().get(generateCouponEventKey(couponEventId))?.let {
            objectMapper.readValue(it, CouponEvent::class.java)
        }
    }

    private fun generateCouponEventKey(couponEventId: Long): String {
        return "$COUPON_EVENT_KEY_PREFIX$couponEventId"
    }
}