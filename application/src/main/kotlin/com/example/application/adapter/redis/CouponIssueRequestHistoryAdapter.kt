package com.example.application.adapter.redis

import com.example.application.port.CouponIssueRequestHistoryPort
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CouponIssueRequestHistoryAdapter(
    private val redisTemplate: RedisTemplate<String, String>
) : CouponIssueRequestHistoryPort {

    companion object {
        private const val USER_REQUEST_KEY_PREFIX = "coupon.user_request.v1:"
        private const val REQUEST_COUNT_KEY_PREFIX = "coupon.request_count.v1:"
        private const val EXPIRE_SECONDS = 60 * 60 * 24 * 7L // 1 week
    }

    override fun setIfNotExists(couponEventId: Long, userId: Long): Boolean {
        return redisTemplate.opsForValue().setIfAbsent(
            generateUserRequestKey(couponEventId, userId), "1",
            Duration.ofSeconds(EXPIRE_SECONDS)
        )!!
    }

    override fun getRequestSequentialNumber(couponEventId: Long): Long {
        val key = generateRequestCountKey(couponEventId)
        val count = redisTemplate.opsForValue().increment(key)
        if (count != null && count == 1L) {
            redisTemplate.expire(key, Duration.ofSeconds(EXPIRE_SECONDS))
        }
        return count!!
    }

    private fun generateUserRequestKey(couponEventId: Long, userId: Long): String {
        return "$USER_REQUEST_KEY_PREFIX$couponEventId:$userId"
    }

    private fun generateRequestCountKey(couponEventId: Long): String {
        return "$REQUEST_COUNT_KEY_PREFIX$couponEventId"
    }
}