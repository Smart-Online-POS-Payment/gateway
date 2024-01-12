package com.sopp.gateway.service

import com.sopp.gateway.client.PaymentClient
import com.sopp.gateway.entity.PaymentRequestEntity
import com.sopp.gateway.model.PaymentModel
import com.sopp.gateway.model.PaymentTransactionModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class PaymentRequestService(
    private val paymentClient: PaymentClient
) {

    suspend fun createPaymentRequest(paymentTransactionModel: PaymentTransactionModel): UUID? {
        return paymentClient.createPaymentRequest(paymentTransactionModel)
    }

    suspend fun cancelPaymentRequest(uuid: UUID, merchantId: String){
        paymentClient.cancelPaymentRequest(merchantId, uuid)
    }

    suspend fun cancelPaymentRequests(merchantId: String){
        paymentClient.cancelPaymentRequests(merchantId)
    }

    suspend fun getPaymentRequestDetail(uuid:UUID, customerId: String): PaymentModel? {
        return paymentClient.getPaymentRequestDetail(uuid, customerId)
    }

}