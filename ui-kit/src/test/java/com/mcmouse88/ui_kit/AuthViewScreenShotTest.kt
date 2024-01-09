package com.mcmouse88.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class AuthViewScreenShotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun `authView default state`() = paparazzi.snapshot {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            AuthView(
                emailInput = "",
                emailError = "",
                isEmailErrorVisible = false,
                onEmailChange = {},
                passwordInput = "",
                passwordError = "",
                isPasswordErrorVisible = false,
                onPasswordChange = {},
                loginError = null,
                isAuthButtonEnabled = true,
                onAuthClick = {}
            )
        }
    }
}