package com.sopp.gateway.service

import com.sopp.gateway.client.AuthClient
import com.sopp.gateway.entity.VerifiedUserEntity
import com.sopp.gateway.model.VerifyUserModel
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authClient: AuthClient
) {

    suspend fun isCustomerVerified(customerId: String): Boolean  {
        return authClient.isCustomerVerified(customerId)
    }

    suspend fun getCustomerVerificationInfo(customerId: String): VerifiedUserEntity {
        return authClient.getCustomerVerificationInfo(customerId)
    }

    suspend fun verifyCustomer(request: VerifyUserModel): Boolean {
        return authClient.verifyCustomer(request)
    }

}