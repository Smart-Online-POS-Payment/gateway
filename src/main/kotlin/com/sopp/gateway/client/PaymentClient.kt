package com.sopp.gateway.client

import com.sopp.gateway.entity.PaymentOrderEntity
import com.sopp.gateway.entity.PaymentRequestEntity
import com.sopp.gateway.model.ResponseModel
import com.sopp.gateway.model.WalletModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.util.UUID

@Service
class PaymentClient(
    @Qualifier("paymentWebClient")
    val client: WebClient
)
{
    suspend fun createPaymentOrder(uuid: UUID, customerId: String): ResponseModel {
        return client.post()
            .uri("/payment-order/$uuid/customer/$customerId")
            .retrieve()
            .awaitBody()
    }

    suspend fun getPaymentsOfUser(customerId: String): List<PaymentOrderEntity>{
        return client.get()
            .uri("/payment-order/customer/$customerId")
            .retrieve()
            .awaitBody()
    }

    suspend fun getPaymentsOfMerchant(merchantId: String): List<PaymentOrderEntity>{
        return client.get()
            .uri("/payment-order/merchant/$merchantId")
            .retrieve()
            .awaitBody()
    }

    suspend fun createPaymentRequest(paymentRequestEntity: PaymentRequestEntity): UUID?{
        return client.post().uri("/payment-request").bodyValue(paymentRequestEntity).retrieve().awaitBody()
    }

    suspend fun cancelPaymentRequest(merchantId: String, uuid: UUID){
        client.get().uri("/payment-request/$merchantId/cancel/$uuid").retrieve()
    }

    suspend fun cancelPaymentRequests(merchantId: String){
        client.get().uri("/payment-request/cancel/$merchantId").retrieve()
    }

    suspend fun getPaymentRequestDetail(uuid: UUID, customerId: String): PaymentRequestEntity?{
        return client.get().uri("payment-request/$uuid/customer/$customerId").retrieve().awaitBody()
    }
}