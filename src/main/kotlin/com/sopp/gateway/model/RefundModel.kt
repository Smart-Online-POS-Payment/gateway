package com.sopp.gateway.model

import java.math.BigDecimal
import java.util.*

data class RefundModel(
    val transactionId: UUID,
    val customerId: String?,
    val amount: BigDecimal,
    val category: String,
)
