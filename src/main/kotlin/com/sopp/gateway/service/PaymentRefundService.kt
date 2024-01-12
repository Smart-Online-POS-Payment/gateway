package com.sopp.gateway.service

import com.sopp.gateway.client.PaymentClient
import com.sopp.gateway.entity.PaymentTransactionEntity
import com.sopp.gateway.model.RefundModel
import com.sopp.gateway.model.ResponseModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PaymentRefundService(
    private val paymentClient: PaymentClient
) {

    suspend fun createRefund(orderId: UUID) {
        paymentClient.createRefundRequest(orderId)
    }

    suspend fun completeRefund(referenceId: UUID) {
        paymentClient.finalizeRefund(referenceId)
    }

    suspend fun getCustomerRefundRequests(customerId: String): List<PaymentTransactionEntity> {
        return paymentClient.getCustomerRefundRequests(customerId)
    }

    suspend fun getMerchantRefundRequests(merchantId: String): List<RefundModel> {
        return paymentClient.getMerchantRefundRequests(merchantId)
    }

    suspend fun getCustomerRefunds(customerId: String): List<PaymentTransactionEntity>{
        return paymentClient.getCustomerRefunds(customerId)
    }

    suspend fun getMerchantRefunds(merchantId: String): List<PaymentTransactionEntity>{
        return paymentClient.getMerchantRefunds(merchantId)
    }
}