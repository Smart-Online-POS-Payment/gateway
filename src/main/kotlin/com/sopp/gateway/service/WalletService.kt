package com.sopp.gateway.service

import com.sopp.gateway.client.WalletClient
import com.sopp.gateway.model.WalletModel
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class WalletService(
    private val walletClient: WalletClient
) {

    suspend fun getWallet(customerId: String): WalletModel {
        return walletClient.getWallet(customerId)
    }
}