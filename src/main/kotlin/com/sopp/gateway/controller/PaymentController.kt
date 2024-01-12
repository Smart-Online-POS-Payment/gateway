package com.sopp.gateway.controller

import com.sopp.gateway.entity.PaymentRequestEntity
import com.sopp.gateway.entity.PaymentTransactionEntity
import com.sopp.gateway.model.PaymentTransactionModel
import com.sopp.gateway.model.ResponseModel
import com.sopp.gateway.model.StatsModel
import com.sopp.gateway.service.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = ["http://localhost:3000"])
class PaymentController(
    private val paymentOrderService: PaymentOrderService,
    private val paymentRequestService: PaymentRequestService,
    private val paymentRefundService: PaymentRefundService,
    private val paymentStatisticsService: PaymentStatisticsService,
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
    suspend fun createPaymentRequest(@RequestHeader("Authorization") authorizationHeader: String, @RequestBody paymentTransactionModel: PaymentTransactionModel): Any? {
        val isValid = firebaseService.validateUserToken(authorizationHeader, paymentTransactionModel.merchantId)
        if (isValid){
            return paymentRequestService.createPaymentRequest(paymentTransactionModel)
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

    @PostMapping("/refund/{orderId}")
    suspend fun createRefund(@PathVariable orderId: UUID) {
        paymentRefundService.createRefund(orderId)
    }

    @PutMapping("/refund/{orderId}")
    suspend fun completeRefund( @PathVariable orderId: UUID) {
        paymentRefundService.completeRefund(orderId)
    }

    @GetMapping("/refund/request/customer/{customerId}")
    suspend fun getCustomerRefundRequests(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable customerId: String): Any {
        val isValid = firebaseService.validateUserToken(authorizationHeader, customerId)
        if (isValid) {
            return paymentRefundService.getCustomerRefundRequests(customerId)
        }
        return ResponseModel("400","Firebase token authentication failed")
    }

    @GetMapping("/refund/request/merchant/{merchantId}")
    suspend fun getMerchantRefundRequests(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable merchantId: String): Any {
        val isValid = firebaseService.validateUserToken(authorizationHeader, merchantId)
        if (isValid) {
            println("entered")
            return paymentRefundService.getMerchantRefundRequests(merchantId)
        }
        return ResponseModel("400","Firebase token authentication failed")
    }

    @GetMapping("/statistics/expenses/customer/{customerId}/category")
    suspend fun getCustomerExpensesPerCategory(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable customerId: String,
    ): MutableMap<String, Double>? {
        print("Kaan")
        val isValid = firebaseService.validateUserToken(authorizationHeader, customerId)
        if (isValid) {
            return paymentStatisticsService.getCustomerExpensesPerCategory(customerId)
        }
        return null
    }

    @GetMapping("/statistics/income/merchant/{merchantId}/category")
    suspend fun getMerchantIncomePerCategory(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable merchantId: String,
    ): List<StatsModel>? {
        val isValid = firebaseService.validateUserToken(authorizationHeader, merchantId)
        if (isValid) {
            return paymentStatisticsService.getMerchantIncomePerCategory(merchantId)
        }
        return null
    }
}