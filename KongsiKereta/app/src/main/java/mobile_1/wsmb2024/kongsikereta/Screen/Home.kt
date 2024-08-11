package mobile_1.wsmb2024.kongsikereta.Screen

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mobile_1.wsmb2024.kongsikereta.Navigate

@Composable
fun Home(navController: NavController){
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Home")
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
                IconButton(onClick = { /*TODO*/ }) {
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

            }
        }
    }
}