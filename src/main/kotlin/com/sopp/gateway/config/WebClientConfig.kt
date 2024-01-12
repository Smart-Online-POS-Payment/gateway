package com.sopp.gateway.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    val clientBuilder: WebClient.Builder,
) {
    @Bean("walletWebClient")
    fun walletWebClient(@Value("\${sopp.server.wallet.url}") baseUrl: String): WebClient {
        return clientBuilder.baseUrl(baseUrl).build()
    }

    @Bean("paymentWebClient")
    fun paymentWebClient(@Value("\${sopp.server.payment.url}") baseUrl: String): WebClient {
        return clientBuilder.baseUrl(baseUrl).build()
    }

    @Bean("notificationWebClient")
    fun notificationWebClient(@Value("\${sopp.server.notification.url}") baseUrl: String): WebClient{
        return clientBuilder.baseUrl(baseUrl).build()
    }

    @Bean("authWebClient")
    fun authWebClient(@Value("\${sopp.server.auth.url}") baseUrl: String): WebClient{
        return clientBuilder.baseUrl(baseUrl).build()
    }
}