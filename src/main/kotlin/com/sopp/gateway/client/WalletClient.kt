package com.sopp.gateway.client

import com.sopp.gateway.model.WalletModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

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
}