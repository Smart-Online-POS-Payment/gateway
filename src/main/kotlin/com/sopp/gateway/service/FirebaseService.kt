package com.sopp.gateway.service

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import org.springframework.stereotype.Service

@Service
class FirebaseService(
    private val firebaseAuth: FirebaseAuth
) {

    fun validateUserToken(token: String, userId: String): Boolean {
        return try {
            val decodedToken = firebaseAuth.verifyIdToken(extractBearerToken(token))
            println(decodedToken)
            println(decodedToken.uid)
            val uid = decodedToken.uid
            userId == uid
        }catch (e: FirebaseException){
            println(e)
            false
        }
    }

    private fun extractBearerToken(authorizationHeader: String?): String {
        if (!authorizationHeader.isNullOrBlank() && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substringAfter("Bearer ").trim()
        }
        throw IllegalArgumentException("Invalid or missing Bearer token")
    }
}