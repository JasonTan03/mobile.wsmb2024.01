package mobile_1.wsmb2024.kongsikereta.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import mobile_1.wsmb2024.kongsikereta.Navigate

class DriverVM: ViewModel(){
    private val auth = Firebase.auth
    private val dB = Firebase.firestore
    val userId = auth.currentUser?.uid

    var isLoading by mutableStateOf(false)
    var isDiable by mutableStateOf(false)
    //For login and Register
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var comfirmPass by mutableStateOf("")

    //For Resister
    var ic by mutableStateOf("")
    var name by mutableStateOf("")
    var isMale by mutableStateOf(false)
    var isDriver by mutableStateOf(false)
    var phone by mutableStateOf("")
    var address by mutableStateOf("")
    var userImg by mutableStateOf("")

    var carImg by mutableStateOf("")
    var plateNo by mutableStateOf("")
    var brand by mutableStateOf("")
    var model by mutableStateOf("")
    var year by mutableStateOf("")
    var maxCap by mutableStateOf(0)

    //Create Ride
    var destination by mutableStateOf("")
    var origin by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")
    var tempfare by mutableStateOf("")
    var fare by mutableStateOf(0.0)


    var userData by mutableStateOf(User())
    var driverData by mutableStateOf(User())
    var rideData by mutableStateOf(Ride())
    var rideList by mutableStateOf(ArrayList<Ride>())

    fun CreateAcc(ctx: android.content.Context, navController: NavController){
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val car = Car(carImg,plateNo,brand, model, year, maxCap )
            val user = auth.uid?.let { it1 -> User(it1,email, password, ic, name, isMale, userImg, phone, address, isDriver = true, car ) }
            if (user != null) {
                auth.uid?.let { it1 ->
                    dB.collection("User").document(it1).set(user).addOnSuccessListener {
                        navController.navigate(Navigate.Login.name)
                        Toast.makeText(ctx,"Sign up success!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    fun Login(ctx: android.content.Context, navController: NavController){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            navController.navigate(Navigate.Home.name)
            Toast.makeText(ctx,"Welcome back!",Toast.LENGTH_SHORT).show()
        }
    }
    fun Logout(navController: NavController){
        auth.signOut()
        navController.navigate(Navigate.Login.name)
    }
    fun getUserData(userId: String){
        dB.collection("User").document(userId).get()
            .addOnSuccessListener { userDa ->
                if(userDa.exists()){
                    userData = userDa.toObject<User>()!!
                }
        }
    }
    fun getDriverUserData(userId: String){
        dB.collection("User").document(userId).get()
            .addOnSuccessListener { userDa ->
                if(userDa.exists()){
                    driverData = userDa.toObject<User>()!!
                }
            }
    }
    fun CreateRide(ctx: Context){
        val ride = userId?.let { Ride(it, userData.car, ArrayList(), destination, origin, date, time, fare ) }
        if (ride != null) {
            dB.collection("Ride").add(ride).addOnSuccessListener {
                Toast.makeText(ctx,"New ride added!",Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun getRide(){
        var count = 0
        dB.collection("Ride").get()
            .addOnSuccessListener {documents ->
                for(document in documents){
                    rideList.add(document.toObject<Ride>())
                    rideList[count].rideID = document.id
                    count++
                }
        }
    }
    fun getRideById(rideId: String){
        var count = 0
        dB.collection("Ride").document(rideId)
            .get()
            .addOnSuccessListener {ride ->
                    rideData = ride.toObject<Ride>()!!
            }
    }
}