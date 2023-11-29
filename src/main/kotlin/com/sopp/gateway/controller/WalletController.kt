package com.sopp.gateway.controller

import com.sopp.gateway.exception.WalletNotFoundException
import com.sopp.gateway.model.WalletModel
import com.sopp.gateway.service.WalletService
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("wallet")
class WalletController(
    private val walletService: WalletService
) {

    @GetMapping("/{customerId}")
    suspend fun getWallet(@PathVariable customerId: String): WalletModel {
        println("Entered...")
        try {
            return walletService.getWallet(customerId)
        }
        catch (ex: WalletNotFoundException){
            throw ResponseStatusException(HttpStatusCode.valueOf(404), ex.localizedMessage, ex)
        }
    }

}