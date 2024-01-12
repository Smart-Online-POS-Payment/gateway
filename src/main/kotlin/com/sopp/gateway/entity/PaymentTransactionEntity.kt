package com.sopp.gateway.entity

import com.sopp.gateway.model.PaymentTransactionModel
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class PaymentTransactionEntity(
    val id: UUID,
    val merchantId: String,
    var customerId: String?,
    val paymentAmount: BigDecimal,
    val category: String,
    var type: String,
    var paymentMessage: String?,
    var paymentDate: Date?,
    var reference: UUID?
)