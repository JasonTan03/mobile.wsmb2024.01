package mobile_1.wsmb2024.kongsikereta

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import mobile_1.wsmb2024.kongsikereta.Screen.CreateRide
import mobile_1.wsmb2024.kongsikereta.Screen.Home
import mobile_1.wsmb2024.kongsikereta.Screen.Login
import mobile_1.wsmb2024.kongsikereta.Screen.Profile
import mobile_1.wsmb2024.kongsikereta.Screen.Register

enum class Navigate {
    Home,
    Login,
    Register,
    CreateRide,
    Profile,

}


@Composable
 fun Navigation(){
     val auth = Firebase.auth
    val loginuser = auth.currentUser
    val navController = rememberNavController()
    var startDes = Navigate.Login.name
    if(loginuser!=null){
        startDes = Navigate.Home.name
    }
    NavHost(
        startDestination = startDes,
        navController = navController
    ){
        composable(route = Navigate.Home.name){
            Home(navController)
        }
        composable(route = Navigate.Login.name){
            Login(navController)
        }
        composable(route = Navigate.Register.name){
            Register(navController)
        }
        composable(route = Navigate.CreateRide.name){
            CreateRide(navController)
        }
        composable(route = Navigate.Profile.name){
            Profile(navController)
        }
    }
 }