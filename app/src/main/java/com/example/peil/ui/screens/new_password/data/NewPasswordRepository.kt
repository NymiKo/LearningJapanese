package com.example.peil.ui.screens.new_password.data

import com.example.peil.data.NetworkResult

interface NewPasswordRepository {
    suspend fun newPassword(idUser: Int, newPassword: String): NetworkResult<Unit>
}