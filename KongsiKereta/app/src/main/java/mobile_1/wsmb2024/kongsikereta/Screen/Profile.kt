package mobile_1.wsmb2024.kongsikereta.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
                .background(Color(0xFF508D4E))
                .height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Profile",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        bottomBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF508D4E))
                .height(64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                IconButton(onClick = { navController.navigate(Navigate.Profile.name) },
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile",
                        modifier = Modifier.size(50.dp))
                }
                Spacer(modifier = Modifier.padding(30.dp))
                IconButton(onClick = { navController.navigate(Navigate.Home.name)}) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "home",
                        modifier = Modifier.size(50.dp))

                }
                Spacer(modifier = Modifier.padding(30.dp))

                IconButton(onClick = { navController.navigate(Navigate.CreateRide.name) }) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "add ride",
                        modifier = Modifier.size(50.dp))
                }
            }
        }
    ) {
        Column (modifier = Modifier.padding(it)){
            Column(modifier = Modifier.padding(24.dp)) {
                if(!isMatch){
                    Spacer(modifier = Modifier.padding(128.dp))
                    Text(text = "Please enter your password to access your profile.",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                    OutlinedTextField(value = comPass, onValueChange = {comPass=it},
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Button(onClick = { if(comPass.equals(profile.userData.password)){
                        isMatch = true
                    }else{
                        Toast.makeText(ctx, "Invalid password. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                    },
                        modifier = Modifier
                            .width(400.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(7.dp),
                    ) {
                        Text(text = "Authenticate")
                    }

                }
                if(isMatch){
                    AsyncImage(model = profile.userData.userImg.toUri(), contentDescription = "pfp",
                        contentScale = ContentScale.Crop, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(64.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
                    )
                    Row{
                        Text(text = "Username:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = profile.userData.name)
                    }
                    Row{
                        Text(text = "IC:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = profile.userData.ic)
                    }
                    Row{
                        Text(text = "Phone Number:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = profile.userData.phone)
                    }
                    Row{
                        Text(text = "Car:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = profile.userData.car.brand +" "+profile.userData.car.model)
                    }
                    Row{
                        Text(text = "Plate Number:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = profile.userData.car.plateNo)
                    }

                    Button(onClick = { navController.navigate(Navigate.Edit.name) },modifier = Modifier.width(400.dp),
                        shape = RoundedCornerShape(7.dp)) {
                        Text(text = "Edit Profile")
                    }
                    Button(onClick = {profile.Logout(navController)},modifier = Modifier.width(400.dp),
                        shape = RoundedCornerShape(7.dp)) {
                        Text(text = "Log out")
                }

                }
            }
        }
    }
}