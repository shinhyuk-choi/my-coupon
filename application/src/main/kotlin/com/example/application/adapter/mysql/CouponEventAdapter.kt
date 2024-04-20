package com.example.application.adapter.mysql


import com.example.application.domain.CouponEvent
import com.example.application.port.CouponEventPort
import org.springframework.stereotype.Component

@Component
class CouponEventAdapter(
    private val couponEventJpaRepository: CouponEventJpaRepository
) : CouponEventPort {
    override fun findById(id: Long): CouponEvent? {
        val couponEventEntity = couponEventJpaRepository.findById(id).orElse(null)
        return couponEventEntity?.toDomain()

    }

    private fun CouponEventEntity.toDomain(): CouponEvent {
        return CouponEvent(
            id = id,
            displayName = displayName,
            expiredAt = expiredAt,
            issueLimit = issueLimit
        )
    }
}