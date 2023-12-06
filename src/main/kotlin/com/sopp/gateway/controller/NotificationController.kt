package com.sopp.gateway.controller

import com.sopp.gateway.model.ResponseModel
import com.sopp.gateway.service.FirebaseService
import com.sopp.gateway.service.NotificationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notification/token")
@CrossOrigin(origins = ["http://localhost:3000"])
class NotificationController(
    private val firebaseService: FirebaseService,
    private val notificationService: NotificationService
) {
    @PostMapping("{token}/user/{userId}")
    suspend fun setToken(@RequestHeader("Authorization") authorizationHeader: String, @PathVariable token: String, @PathVariable userId: String) {
        if(!firebaseService.validateUserToken(authorizationHeader, userId)) throw Exception("Firebase token verification failed")
        notificationService.setToken(userId, token)
    }
}