package com.sopp.gateway.model

import java.math.BigDecimal

data class PaymentTransactionModel(
    var merchantId: String,
    var paymentAmount: BigDecimal,
    var category: String,
    var paymentMessage: String?
){
    enum class Type {
        RequestSale,
        FinalizeSale,
        RequestRefund,
        FinalizeRefund
    }

    enum class Category {
        Groceries,
        Clothing,
        Electronics,
        Tickets,
        CarRentals,
        Restaurants,
        Coffee,
        Charity,
        Rent,
        Gaming,
        Other
    }
}
