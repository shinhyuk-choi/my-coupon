package com.example.application.port

import com.example.application.domain.CouponEvent

interface CouponEventCachePort {

    fun set(couponEvent: CouponEvent)

    fun get(couponEventId: Long): CouponEvent?
}