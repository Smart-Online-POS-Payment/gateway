package com.sopp.gateway.service

import com.sopp.gateway.client.PaymentClient
import com.sopp.gateway.entity.PaymentOrderEntity
import com.sopp.gateway.model.ResponseModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class PaymentOrderService(
    private val paymentClient: PaymentClient
) {
    suspend fun createPaymentOrder(uuid: UUID, customerId: String): ResponseModel {
        return paymentClient.createPaymentOrder(uuid, customerId)
    }

    suspend fun getPaymentsOfUser(customerId: String): List<PaymentOrderEntity> {
        return paymentClient.getPaymentsOfUser(customerId)
    }

    suspend fun getPaymentsOfMerchant(merchantId: String): List<PaymentOrderEntity> {
        return paymentClient.getPaymentsOfMerchant(merchantId)
    }
}