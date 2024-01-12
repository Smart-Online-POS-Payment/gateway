package com.sopp.gateway.controller

import com.sopp.gateway.exception.WalletNotFoundException
import com.sopp.gateway.model.CardModel
import com.sopp.gateway.model.ResponseModel
import com.sopp.gateway.model.WalletModel
import com.sopp.gateway.service.FirebaseService
import com.sopp.gateway.service.WalletService
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@RestController
@RequestMapping("wallet")
@CrossOrigin(origins = ["http://localhost:3000"])
class WalletController(
    private val walletService: WalletService,
    private val firebaseService: FirebaseService
) {

    @GetMapping("/{customerId}")
    suspend fun getWallet(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable customerId: String
    ): WalletModel {
        if(!firebaseService.validateUserToken(authorizationHeader, customerId)) throw Exception("Firebase token verification failed")
        try {
            return walletService.getWallet(customerId)
        }
        catch (ex: WalletNotFoundException){
            throw ResponseStatusException(HttpStatusCode.valueOf(404), ex.localizedMessage, ex)
        }
    }

    @PutMapping("/card-payment/{customerId}/amount/{amount}")
    suspend fun addMoneyToWalletFromCard(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable customerId: String,
        @PathVariable amount: BigDecimal,
        @RequestBody cardModel: CardModel,
    ): ResponseModel {

        if(!firebaseService.validateUserToken(authorizationHeader, customerId)) {
            throw Exception("Firebase token verification failed")
            return ResponseModel("409", "Firebase authentication failed")
        }
        return walletService.addMoneyToWalletFromCard(customerId, amount, cardModel)

    }

    @GetMapping("/{customerId}/cards")
    suspend fun getCards(
        @RequestHeader("Authorization") authorizationHeader: String,
        @PathVariable customerId: String,
    ): List<CardModel> {
        if(!firebaseService.validateUserToken(authorizationHeader, customerId)) throw Exception("Firebase token verification failed")
        return walletService.getCards(customerId)
    }

    @PostMapping("/{customerId}/cards")
    suspend fun saveCard(
        @RequestHeader("Authorization") authorizationHeader: String,
        @RequestBody cardModel: CardModel,
        @PathVariable customerId: String,
    ) {
        if(!firebaseService.validateUserToken(authorizationHeader, customerId)) throw Exception("Firebase token verification failed")
        walletService.saveCard(customerId, cardModel)
    }

}