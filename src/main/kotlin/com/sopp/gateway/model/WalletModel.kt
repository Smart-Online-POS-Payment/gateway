package com.sopp.gateway.model

import java.math.BigDecimal
import java.util.UUID

data class WalletModel(
    val id: UUID,
    val customerId: String,
    val balance: BigDecimal
)
