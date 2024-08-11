package mobile_1.wsmb2024.kongsikereta.Screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM


@Composable
fun Register(navController: NavController, reg: DriverVM = viewModel()){
    val ctx = LocalContext.current
    var uri by remember { mutableStateOf<Uri?>(null) }
//    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uris ->
//        uris?.let{
//            uri = uris
//        }
//        
//    }


    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        Column(modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {


            AsyncImage(
                model = uri,
                contentDescription = "avatar",
                //onState = pickMedia.launch(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
            OutlinedTextField(
                value = reg.email,
                onValueChange = {reg.email = it},
                label = { Text(text = "Email")},
                singleLine = true,
                isError = false,)
            OutlinedTextField(
                value = reg.password,
                onValueChange = {reg.password = it},
                label = { Text(text = "Password")},
                singleLine = true,
                isError = false,)
            OutlinedTextField(
                value = reg.comfirmPass,
                onValueChange = {reg.comfirmPass = it},
                label = { Text(text = "Confirm Password")},
                singleLine = true,
                isError = false,)
            OutlinedTextField(
                value = reg.ic,
                onValueChange = {reg.ic = it},
                label = { Text(text = "IC")},
                singleLine = true,
                isError = false,)
            OutlinedTextField(
                value = reg.name,
                onValueChange = {reg.name = it},
                label = { Text(text = "Username")},
                singleLine = true,
                isError = false,)
            OutlinedTextField(
                value = reg.phone,
                onValueChange = {reg.phone = it},
                label = { Text(text = "Phone number")},
                singleLine = true,
                isError = false,)
            OutlinedTextField(
                value = reg.address,
                onValueChange = {reg.address = it},
                label = { Text(text = "Address")},
                singleLine = true,
                isError = false,)


            var checked by remember { mutableStateOf(false) }

            Row {
                Text(text = "Are you a driver? ")
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
            }
            if(checked) {
                AsyncImage(
                    model = uri,
                    contentDescription = "avatar",
                    //onState = pickMedia.launch(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                OutlinedTextField(
                    value = reg.plateNo,
                    onValueChange = { reg.plateNo = it },
                    label = { Text(text = "plateNo") },
                    singleLine = true,
                    isError = false,
                )
                OutlinedTextField(
                    value = reg.brand,
                    onValueChange = { reg.brand = it },
                    label = { Text(text = "brand") },
                    singleLine = true,
                    isError = false,
                )
                OutlinedTextField(
                    value = reg.model,
                    onValueChange = { reg.model = it },
                    label = { Text(text = "model") },
                    singleLine = true,
                    isError = false,
                )
                OutlinedTextField(
                    value = reg.year,
                    onValueChange = { reg.year = it },
                    label = { Text(text = "year") },
                    singleLine = true,
                    isError = false,
                )
                Row{
                    Button(onClick = { reg.maxCap-- }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "minus")
                    }
                    Text(text = reg.maxCap.toString())
                    Button(onClick = { reg.maxCap++ }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "minus")

                    }
                }
            }

            Button(onClick = { reg.CreateAcc(ctx, navController) }) {
                Text(text = "Sign up")
            }
            Row {
                Text(text = "Have an Account? ")
                Text(text = "Login.",
                    modifier = Modifier.clickable { navController.navigate(Navigate.Login.name)  })
            }
        }
    }
}