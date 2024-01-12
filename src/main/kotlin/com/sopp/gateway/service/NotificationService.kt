package com.sopp.gateway.service

import com.sopp.gateway.client.NotificationClient
import com.sopp.gateway.model.ResponseModel
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationClient: NotificationClient
) {

    suspend fun setToken(token: String, userId: String): Boolean {
        return notificationClient.setToken(token, userId)
    }
}