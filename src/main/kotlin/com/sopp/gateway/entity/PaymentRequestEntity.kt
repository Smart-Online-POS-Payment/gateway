package com.sopp.gateway.entity

import java.math.BigDecimal
import java.util.*

data class PaymentRequestEntity(
    val id: UUID = UUID.randomUUID(),
    var merchantId: String,
    var paymentAmount: BigDecimal,
    var paymentMessage: String
){
    constructor() : this(UUID.randomUUID(), UUID.randomUUID().toString(), BigDecimal.ONE, "")
}

