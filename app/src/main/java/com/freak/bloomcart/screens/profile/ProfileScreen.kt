package com.freak.bloomcart.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.freak.bloomcart.model.UserProfile

@Composable
fun ProfileScreen(navController: NavController, onSignOut: ()-> Unit) {
    val currentUser = UserProfile("77", "James Bond", "james@bond.com")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(32.dp))

        Box(modifier = Modifier.size(120.dp).clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = currentUser.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold)

        Text(text = currentUser.email,
            style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))


        Button(onClick = onSignOut,
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
            )
        {
            Text("Sign Out", modifier = Modifier.padding(4.dp))
        }

    }
}

@Composable
@Preview (showBackground = true)
fun showProfile() {
    //ProfileScreen({})
}