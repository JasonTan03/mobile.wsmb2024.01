package mobile_1.wsmb2024.kongsikereta.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM
import mobile_1.wsmb2024.kongsikereta.ViewModel.SelectVM

@Composable
fun Home(navController: NavController, view: DriverVM = viewModel(), selectVM: SelectVM){
    val auth = Firebase.auth
    val userID = auth.currentUser?.uid
    if(userID != null){
        LaunchedEffect(userID) {
            view.getUserData(userID)
            view.getRide()
        }
    }
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
                view.rideList.forEach{ride ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { selectVM.rideID = ride.rideID
                        navController.navigate(Navigate.ViewRideDetail.name)}){
                        Column(modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight()
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(8.dp))) {
                            AsyncImage(model ="https://example.com/image.jpg"
                                , contentDescription = "test")
                        }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(4.dp)){
                            Row {
                                Column {
                                    Text(text = "Destination")
                                    Text(text = ride.destination)
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Column {
                                    Text(text = "Origin")
                                    Text(text = ride.origin)
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}