package com.example.application.adapter.mysql


import com.example.application.domain.Coupon
import com.example.application.domain.CouponEvent
import com.example.application.domain.CouponInfo
import com.example.application.port.CouponPort
import org.springframework.stereotype.Component

@Component
class CouponAdapter(
    private val couponJpaRepository: CouponJpaRepository
) : CouponPort {


    override fun save(coupon: Coupon): Coupon {
        return toDomain(couponJpaRepository.save(toEntity(coupon)))
    }

    override fun listByUserId(userId: Long): List<CouponInfo> {
        return couponJpaRepository.findAllByUserId(userId)
            .map { couponEntity ->
                CouponInfo.generate(
                    coupon = toDomain(couponEntity),
                    couponEvent = CouponEvent(
                        id=couponEntity.couponEvent!!.id,
                        displayName=couponEntity.couponEvent.displayName,
                        expiredAt=couponEntity.couponEvent.expiredAt,
                        issueLimit=couponEntity.couponEvent.issueLimit
                    )
                )
            }
    }

    private fun toEntity(coupon: Coupon): CouponEntity {
        return CouponEntity(
            id = coupon.id,
            couponEventId = coupon.couponEventId,
            userId = coupon.userId,
            issuedAt = coupon.issuedAt,
            usedAt = coupon.usedAt
        )
    }

    private fun toDomain(couponEntity: CouponEntity): Coupon {
        return Coupon(
            id = couponEntity.id,
            couponEventId = couponEntity.couponEventId,
            userId = couponEntity.userId,
            issuedAt = couponEntity.issuedAt,
            usedAt = couponEntity.usedAt
        )
    }
}