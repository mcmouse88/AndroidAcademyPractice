package com.mcmouse88.ui_kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun AuthView(
    emailInput: String,
    emailError: String,
    isEmailErrorVisible: Boolean,
    onEmailChange: (String) -> Unit,
    passwordInput: String,
    passwordError: String,
    isPasswordErrorVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    loginError: Int?,
    isAuthButtonEnabled: Boolean,
    onAuthClick: () -> Unit,
    snackbarState: SnackbarHostState = remember { SnackbarHostState() }
) {
    if (loginError != null) {
        val message = stringResource(id = loginError)

        LaunchedEffect(key1 = snackbarState) {
            snackbarState.showSnackbar(
                message = message
            )
        }
    }

    Box {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.space_normal))
        ) {
            OutlinedTextField(
                value = emailInput,
                onValueChange = onEmailChange,
                label = { Text(text = stringResource(id = R.string.email)) },
                isError = isEmailErrorVisible,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            if (isEmailErrorVisible) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = passwordInput,
                onValueChange = onPasswordChange,
                label = { Text(text = stringResource(id = R.string.password)) },
                isError = isPasswordErrorVisible,
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    PasswordVisibilityToggleButton(isVisible = passwordVisible) {
                        passwordVisible = !passwordVisible
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            if (isPasswordErrorVisible) {
                Text(
                    text = passwordError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(
                onClick = onAuthClick,
                enabled = isAuthButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.auth))
            }
        }

        SnackbarHost(
            hostState = snackbarState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = dimensionResource(id = R.dimen.space_normal))
        )
    }
}

@Composable
private fun PasswordVisibilityToggleButton(
    isVisible: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        Icon(
            imageVector = if (isVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            },
            contentDescription = if (isVisible) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }
        )
    }
}

@Preview
@Composable
private fun AuthViewPreview() {
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