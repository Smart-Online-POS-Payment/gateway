package com.sopp.gateway.entity

import java.math.BigDecimal
import java.util.*

data class PaymentOrderEntity(
    val orderId: UUID,
    val amount: BigDecimal,
    val description: String?,
    val category: String,
    val date: Date,
){
    constructor(): this(UUID.randomUUID(),  BigDecimal.ONE, UUID.randomUUID().toString(),"", Date())
}