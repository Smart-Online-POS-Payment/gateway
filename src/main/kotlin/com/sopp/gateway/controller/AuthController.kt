package com.sopp.gateway.controller

import com.sopp.gateway.entity.VerifiedUserEntity
import com.sopp.gateway.model.VerifyUserModel
import com.sopp.gateway.service.AuthService
import com.sopp.gateway.service.FirebaseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("verify")
@CrossOrigin(origins = ["http://localhost:3000"])
class AuthController(
    private val authService: AuthService,
    private val firebaseService: FirebaseService
) {

    @PostMapping
    suspend fun verifyCustomer(
        @RequestHeader("Authorization") authorizationHeader: String,
        @RequestBody body: VerifyUserModel
    ): Boolean {
        if(!firebaseService.validateUserToken(authorizationHeader, body.userId)) throw Exception("Firebase token verification failed")
        return authService.verifyCustomer(body)
    }

    @GetMapping("/customer/{customerId}/is-verified")
    suspend fun isCustomerVerified(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable customerId: String,
    ): Boolean {
        if(!firebaseService.validateUserToken(authorizationHeader, customerId)) throw Exception("Firebase token verification failed")
        return authService.isCustomerVerified(customerId)
    }

    @GetMapping("/customer/{customerId}")
    suspend fun getCustomerVerificationInfo(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable customerId: String,
    ): VerifiedUserEntity {
        if(!firebaseService.validateUserToken(authorizationHeader, customerId)) throw Exception("Firebase token verification failed")
        return authService.getCustomerVerificationInfo(customerId)
    }
}