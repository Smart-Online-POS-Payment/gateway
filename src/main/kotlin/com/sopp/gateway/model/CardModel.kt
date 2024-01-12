package com.sopp.gateway.model

data class CardModel(
    val cardholderName: String,
    val cardNumber: String,
    val cvvCode: String,
    val expiryDate: String
)
