package com.sopp.gateway.entity

data class VerifiedUserEntity(
    val userId: String,
    val tc: Long,
    val firstname: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val openAddress: String,
    val city: String,
    val country: String,
)
