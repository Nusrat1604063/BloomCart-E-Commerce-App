package com.freak.bloomcart.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // you wrote: val authState = true
    // keeping it as-is (you said you'll change it later for auth)
    val authState = true
    if(authState) {
        onSignUpSuccess()
    }

    // rest of your UI code would go here...
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Crete Account",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),

        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            isError = passwordError != null,
            supportingText =
                {
                    passwordError?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (password != confirmPassword) {
                    passwordError = "Passwords do not match"
                } else if (password.length < 6) {
                    passwordError = "Password should be at least 6 characters"
                } else {
                    passwordError = null
                    //call viewmodel to process signup
                    onNavigateToLogin()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
//            enabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() &&
//            !isAuthState.Loading

        ) {
            //if Authstate.loading
            //display circular progress indicator

            Text("Sign Up")

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text("Already have an account? Login")
            }


        }

    }
}