package com.sopp.gateway.service

import com.sopp.gateway.client.PaymentClient
import com.sopp.gateway.model.StatsModel
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class PaymentStatisticsService(
    private val paymentClient: PaymentClient
) {

    suspend fun getCustomerExpensesPerCategory(
        customerId: String
    ): MutableMap<String, Double> {
        return paymentClient.getCustomerExpensesPerCategory(customerId)
    }

    suspend fun getMerchantIncomePerCategory(
        merchantId: String
    ): List<StatsModel> {
        return paymentClient.getMerchantIncomePerCategory(merchantId)
    }
}