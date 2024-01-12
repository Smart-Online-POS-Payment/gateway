package com.sopp.gateway.model

import java.math.BigDecimal
import java.util.*

data class PaymentModel(
    val orderId: UUID,
    val amount: BigDecimal,
    val description: String?,
    val category: String,
    val date: Date,
)