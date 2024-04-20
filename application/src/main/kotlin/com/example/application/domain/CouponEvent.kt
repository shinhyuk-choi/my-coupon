package com.example.application.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class CouponEvent(
    val id: Long,
    val displayName: String,
    val expiredAt: LocalDateTime,
    val issueLimit: Long,
){
    @JsonIgnore
    fun isExpired(): Boolean {
        return expiredAt.isBefore(LocalDateTime.now())
    }
}

