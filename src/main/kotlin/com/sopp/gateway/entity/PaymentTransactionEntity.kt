package com.sopp.gateway.entity

import com.sopp.gateway.model.PaymentTransactionModel
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class PaymentTransactionEntity(
    @Id
    val id: UUID,
    val merchantId: String,
    var customerId: String?,
    val paymentAmount: BigDecimal,
    @Enumerated(EnumType.STRING)
    val category: PaymentTransactionModel.Category,
    @Enumerated(EnumType.STRING)
    var type: PaymentTransactionModel.Type,
    var paymentMessage: String?,
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var paymentDate: Date?,
    var reference: UUID?
) {
    constructor() : this(UUID.randomUUID(), "", null,BigDecimal.ZERO, PaymentTransactionModel.Category.Other, PaymentTransactionModel.Type.RequestSale, null, null, null)
    constructor(paymentTransactionModel: PaymentTransactionModel): this(UUID.randomUUID(), paymentTransactionModel.merchantId, paymentTransactionModel.customerId, paymentTransactionModel.paymentAmount, paymentTransactionModel.category, paymentTransactionModel.type, paymentTransactionModel.paymentMessage, null, null)

    constructor(paymentTransactionEntity: PaymentTransactionEntity, reference: UUID?): this(UUID.randomUUID(), paymentTransactionEntity.merchantId, paymentTransactionEntity.customerId, paymentTransactionEntity.paymentAmount, paymentTransactionEntity.category, PaymentTransactionModel.Type.RequestRefund, paymentTransactionEntity.paymentMessage, Date.from(Instant.now()), reference)
}