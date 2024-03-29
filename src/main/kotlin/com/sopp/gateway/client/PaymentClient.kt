package com.sopp.gateway.client

import com.sopp.gateway.entity.PaymentOrderEntity
import com.sopp.gateway.entity.PaymentRequestEntity
import com.sopp.gateway.entity.PaymentTransactionEntity
import com.sopp.gateway.model.*
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

    suspend fun createPaymentRequest(paymentTransactionModel: PaymentTransactionModel): UUID?{
        return client.post().uri("/payment-request").bodyValue(paymentTransactionModel).retrieve().awaitBody()
    }

    suspend fun cancelPaymentRequest(merchantId: String, uuid: UUID){
        client.get().uri("/payment-request/$merchantId/cancel/$uuid").retrieve().awaitBody<Unit>()
    }

    suspend fun cancelPaymentRequests(merchantId: String){
        client.get().uri("/payment-request/cancel/$merchantId").retrieve().awaitBody<Unit>()
    }

    suspend fun getPaymentRequestDetail(uuid: UUID, customerId: String): PaymentModel?{
        return client.get().uri("payment-request/$uuid/customer/$customerId").retrieve().awaitBody()
    }

    suspend fun createRefundRequest(id: UUID){
        client.post().uri("/refund/$id").retrieve().awaitBody<Unit>()
    }

    suspend fun finalizeRefund(id: UUID){
        client.put().uri("/refund/$id").retrieve().awaitBody<Unit>()
    }

    suspend fun getCustomerRefundRequests(customerId: String): List<PaymentTransactionEntity>{
        return client.get().uri("refund/request/customer/$customerId").retrieve().awaitBody()
    }

    suspend fun getMerchantRefundRequests(merchantId: String): List<RefundModel>{
        return client.get().uri("refund/request/merchant/$merchantId").retrieve().awaitBody()
    }

    suspend fun getCustomerRefunds(customerId: String): List<PaymentTransactionEntity>{
        return client.get().uri("refund/customer/$customerId").retrieve().awaitBody()
    }

    suspend fun getMerchantRefunds(merchantId: String): List<PaymentTransactionEntity>{
        return client.get().uri("refund/merchant/$merchantId").retrieve().awaitBody()
    }

    suspend fun getCustomerExpensesPerCategory(customerId: String): MutableMap<String, Double>{
        return client.get().uri("/statistics/expenses/customer/${customerId}/category").retrieve().awaitBody()
    }

    suspend fun getMerchantIncomePerCategory(merchantId: String): List<StatsModel> {
        return client.get().uri("/statistics/income/merchant/${merchantId}/category").retrieve().awaitBody()
    }
}