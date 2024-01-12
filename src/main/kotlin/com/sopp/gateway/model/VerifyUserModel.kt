package com.sopp.gateway.model

data class VerifyUserModel(
    val userId: String,
    val tc: Long,
    val firstname: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val birthYear: Int,
    val openAddress: String,
    val city: String,
    val country: String = "Turkey",
)