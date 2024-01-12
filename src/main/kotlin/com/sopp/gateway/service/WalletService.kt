package com.sopp.gateway.service

import com.sopp.gateway.client.WalletClient
import com.sopp.gateway.model.CardModel
import com.sopp.gateway.model.ResponseModel
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

    suspend fun getCards(customerId: String): List<CardModel>{
        return walletClient.getCards(customerId)
    }

    suspend fun saveCard(customerId: String, cardModel: CardModel){
        walletClient.saveCard(customerId, cardModel)
    }

    suspend fun addMoneyToWalletFromCard(customerId: String,amount: BigDecimal, cardModel: CardModel): ResponseModel {
        return walletClient.addMoneyToWalletFromCard(customerId, amount, cardModel)
    }
}