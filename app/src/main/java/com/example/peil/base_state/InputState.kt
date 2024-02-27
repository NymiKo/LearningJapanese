package com.example.peil.base_state

enum class InputType {
    TEXT, EMAIL, PASSWORD, NICKNAME, CODE
}
data class InputState(
    val text: String = "",
    val isError: Boolean = false,
    val type: InputType,
    val errorMessage: String = ""
)
