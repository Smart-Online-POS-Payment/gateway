package com.sopp.gateway.client

import com.sopp.gateway.entity.VerifiedUserEntity
import com.sopp.gateway.model.VerifyUserModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class AuthClient(
    @Qualifier("authWebClient")
    val client: WebClient
) {

    suspend fun verifyCustomer(verifyUserModel: VerifyUserModel): Boolean {
        return client.post().uri("").bodyValue(verifyUserModel).retrieve().awaitBody<Boolean>()
    }

    suspend fun isCustomerVerified(customerId: String): Boolean {
        return client.get().uri("/customer/$customerId/is-verified").retrieve().awaitBody<Boolean>()
    }

    suspend fun getCustomerVerificationInfo(customerId: String): VerifiedUserEntity {
        return client.get().uri("/customer/$customerId").retrieve().awaitBody<VerifiedUserEntity>()
    }
}