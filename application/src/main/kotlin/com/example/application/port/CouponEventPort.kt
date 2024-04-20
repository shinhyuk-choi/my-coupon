package com.example.application.port

import com.example.application.domain.CouponEvent

interface CouponEventPort {

    fun findById(id: Long): CouponEvent?
}