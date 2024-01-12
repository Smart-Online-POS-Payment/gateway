package com.sopp.gateway.client

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class NotificationClient(
    @Qualifier("notificationWebClient")
    val client: WebClient
) {
    suspend fun setToken(token: String, userId: String): Boolean{
        println(token)
        return client.post()
            .uri("/$token/user/$userId")
            .retrieve()
            .awaitBody()
    }
}