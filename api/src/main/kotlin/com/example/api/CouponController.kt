package com.example.api



import com.example.application.CouponIssueRequestHistoryService
import com.example.application.CouponIssueRequestService
import com.example.application.UsableCouponListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val couponIssueRequestHistoryService: CouponIssueRequestHistoryService,
    private val couponIssueRequestService: CouponIssueRequestService,
    private val usableCouponListService: UsableCouponListService,
) {

    @PostMapping
    fun issue(@RequestBody request: CouponIssueRequest): ResponseEntity<String> {
        if (!couponIssueRequestHistoryService.isFirstRequest(
                request.couponEventId,
                request.userId
            )
        ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already requested")
        }
        if (!couponIssueRequestHistoryService.hasRemainingCoupon(request.couponEventId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No remaining coupon")
        }
        couponIssueRequestService.queue(request.couponEventId, request.userId)
        return ResponseEntity.ok("OK")
    }

    @GetMapping
    fun list(@RequestParam userId: Long): ResponseEntity<List<CouponInfoResponse>> {
        val couponInfos = usableCouponListService.listByUserId(userId)
        val couponInfoResponses = couponInfos.map { CouponInfoResponse.from(it) }
        return ResponseEntity.ok(couponInfoResponses)
    }
}