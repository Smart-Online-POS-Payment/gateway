package com.sopp.gateway.client

import com.sopp.gateway.model.CardModel
import com.sopp.gateway.model.ResponseModel
import com.sopp.gateway.model.WalletModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.math.BigDecimal

@Service
class WalletClient(
    @Qualifier("walletWebClient")
    val client: WebClient, )
{
    suspend fun getWallet(customerId: String): WalletModel{
        return client.get()
            .uri("/{customerId}",customerId)
            .retrieve()
            .awaitBody()
    }

    suspend fun getCards(customerId: String): List<CardModel>{
        return client.get()
            .uri("/{customerId}/cards", customerId)
            .retrieve()
            .awaitBody()
    }

    suspend fun saveCard(customerId: String, cardModel: CardModel) {
        try {
            client.post()
                .uri("/{customerId}/cards", customerId)  // Adjusted the order of parameters
                .bodyValue(cardModel)
                .retrieve().awaitBody<Unit>()
        }
        catch (e: Exception){
            println(e.message)
        }

    }

    suspend fun addMoneyToWalletFromCard(
        @PathVariable customerId: String,
        @PathVariable amount: BigDecimal,
        @RequestBody cardModel: CardModel,
    ): ResponseModel {
        return client.put().uri("/card-payment/${customerId}/amount/${amount}").bodyValue(cardModel)
            .retrieve().awaitBody()
    }
}