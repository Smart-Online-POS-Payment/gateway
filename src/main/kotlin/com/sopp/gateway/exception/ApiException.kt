package com.sopp.gateway.exception

class WalletNotFoundException(message: String) : RuntimeException(message) {

}

class PaymentExceedsBalanceException(message: String): RuntimeException(message) {

}