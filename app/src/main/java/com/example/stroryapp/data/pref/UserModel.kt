package com.example.stroryapp.data.pref

data class UserModel(
    val email: String,
    val token: String,
    val name: String,
    val isLogin: Boolean = false
)