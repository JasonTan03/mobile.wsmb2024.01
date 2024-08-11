package mobile_1.wsmb2024.kongsikereta.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM

@Composable
fun Profile(navController: NavController, profile: DriverVM = viewModel()){
    var ctx = LocalContext.current
    val auth = Firebase.auth
    val userID = auth.currentUser?.uid
    if(userID != null){
        LaunchedEffect(userID) {
            profile.getUserData(userID)
        }
    }
    var isMatch by remember{ mutableStateOf(false)}
    var comPass by remember{ mutableStateOf("")}
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Profile")
            }
        },
        bottomBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                IconButton(onClick = { navController.navigate(Navigate.Profile.name) }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile")
                }
                IconButton(onClick = { navController.navigate(Navigate.Home.name)}) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "home")
                }
                IconButton(onClick = { navController.navigate(Navigate.CreateRide.name) }) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "add ride")

                }
            }
        }
    ) {
        Column (modifier = Modifier.padding(it)){
            Column(modifier = Modifier.padding(24.dp)) {
                if(!isMatch){
                    Text(text = "Please enter your password to access your profile.")
                    OutlinedTextField(value = comPass, onValueChange = {comPass=it})
                    Button(onClick = { if(comPass.equals(profile.userData.password)){
                        isMatch = true
                    }else{
                        Toast.makeText(ctx, "Invalid password. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                    }) {
                        Text(text = "Authenticate")
                    }

                }
                if(isMatch){
                    auth.uid?.let { it1 -> Text(text = it1) }
                    Text(text = profile.userData.name)
                    Button(onClick = {profile.Logout(navController)}) {
                        Text(text = "Log out")
                }

                }
            }
        }
    }
}