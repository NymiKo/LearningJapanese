package com.example.peil.ui.screens.new_password.data

import com.example.peil.ui.screens.new_password.data.model.ChangePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewPasswordService {
    @POST("changePassword.php")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Response<Unit>
}