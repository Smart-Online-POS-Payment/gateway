package com.sopp.gateway.controller

import com.sopp.gateway.entity.PaymentRequestEntity
import com.sopp.gateway.model.ResponseModel
import com.sopp.gateway.service.FirebaseService
import com.sopp.gateway.service.PaymentOrderService
import com.sopp.gateway.service.PaymentRequestService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("payment")
@CrossOrigin(origins = ["http://localhost:3000"])
class PaymentController(
    private val paymentOrderService: PaymentOrderService,
    private val paymentRequestService: PaymentRequestService,
    private val firebaseService: FirebaseService
) {

    @PostMapping("/payment-order/{uuid}/customer/{customerId}")
    suspend fun createPaymentOrder(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable uuid: UUID, @PathVariable customerId: String): ResponseModel {
        val isValid = firebaseService.validateUserToken(authorizationHeader, customerId)
        if (isValid){
            return paymentOrderService.createPaymentOrder(uuid, customerId)
        }

        return ResponseModel("400","Firebase token authentication failed")

    }

    @GetMapping("/payment-order/customer/{customerId}")
    suspend fun getPaymentsOfUser(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable customerId: String): Any {
        val isValid = firebaseService.validateUserToken(authorizationHeader, customerId)
        if (isValid){
            return paymentOrderService.getPaymentsOfUser(customerId)
        }

        return ResponseModel("400","Firebase token authentication failed")
    }

    @GetMapping("/payment-order/merchant/{merchantId}")
    suspend fun getPaymentsOfMerchant(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable merchantId: String): Any {
        val isValid = firebaseService.validateUserToken(authorizationHeader, merchantId)
        if (isValid){
            return paymentOrderService.getPaymentsOfMerchant(merchantId)
        }

        return ResponseModel("400","Firebase token authentication failed")
    }

    @PostMapping("/payment-request")
    suspend fun createPaymentRequest(@RequestHeader("Authorization") authorizationHeader: String, @RequestBody paymentRequestEntity: PaymentRequestEntity): ResponseModel {
        val isValid = firebaseService.validateUserToken(authorizationHeader, paymentRequestEntity.merchantId)
        if (isValid){
            paymentRequestService.createPaymentRequest(paymentRequestEntity)
        }

        return ResponseModel("400","Firebase token authentication failed")
    }

    @DeleteMapping("/payment-request/{merchantId}/cancel/{uuid}")
    suspend fun cancelPaymentRequest(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable merchantId: String, @PathVariable uuid: UUID): ResponseModel {
        val isValid = firebaseService.validateUserToken(authorizationHeader, merchantId)
        if (isValid){
            paymentRequestService.cancelPaymentRequest(uuid, merchantId)
        }

        return ResponseModel("400","Firebase token authentication failed")
    }

    @DeleteMapping("/payment-request/cancel/{merchantId}")
    suspend fun cancelPaymentRequests(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable merchantId: String): ResponseModel {
        val isValid = firebaseService.validateUserToken(authorizationHeader, merchantId)
        if (isValid){
            paymentRequestService.cancelPaymentRequests(merchantId)
        }

        return ResponseModel("400","Firebase token authentication failed")
    }

    @GetMapping("/payment-request/{uuid}/customer/{customerId}")
    suspend fun getPaymentRequestDetail(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable uuid: UUID, @PathVariable customerId: String): Any? {
        val isValid = firebaseService.validateUserToken(authorizationHeader, customerId)
        if (isValid){
            return paymentRequestService.getPaymentRequestDetail(uuid, customerId)
        }

        return ResponseModel("400","Firebase token authentication failed")
    }
}