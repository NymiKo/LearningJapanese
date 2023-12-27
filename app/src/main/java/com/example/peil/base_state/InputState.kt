package com.example.peil.base_state

enum class InputType {
    TEXT, EMAIL, PASSWORD
}
data class InputState(
    val text: String = "",
    val isValid: Boolean = true,
    val type: InputType,
    val errorMessage: String = ""
)
