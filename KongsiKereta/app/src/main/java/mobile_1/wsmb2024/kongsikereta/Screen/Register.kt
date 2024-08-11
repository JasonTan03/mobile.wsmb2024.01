package mobile_1.wsmb2024.kongsikereta.Screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM


@Composable
fun Register(navController: NavController, reg: DriverVM = viewModel()){
    val ctx = LocalContext.current
    var uri1 by remember { mutableStateOf<Uri?>(null) }
    var uri2 by remember { mutableStateOf<Uri?>(null) }
    val pfp = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uris ->
        uris?.let{
            uri1 = uris
        }

    }
    val car = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uris ->
        uris?.let{
            uri2 = uris
        }

    }


    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
        Column(modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {


            AsyncImage(
                model = uri1,
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
                    .size(128.dp)
                    .clickable {
                        pfp.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
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
                isError = false,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = reg.comfirmPass,
                onValueChange = {reg.comfirmPass = it},
                label = { Text(text = "Confirm Password")},
                singleLine = true,
                isError = false,
                visualTransformation = PasswordVisualTransformation()
            )
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
                singleLine = false,
                isError = false,
                modifier = Modifier.height(80.dp))

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){

                Text(text = "Famale", fontSize = 20.sp)
                Switch(
                    checked = reg.isMale,
                    onCheckedChange = {
                        reg.isMale = it
                    }
                )
                Text(text = "Male", fontSize = 20.sp)

            }


                AsyncImage(
                    model = uri2,
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
                        .size(128.dp)
                        .clickable {
                            car.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Button(onClick = { reg.minusCap() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "minus")
                    }
                    Text(text = reg.maxCap.toString(), fontSize = 20.sp)
                    Button(onClick = { reg.addCap() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "minus")
                    }
                }
            Button(onClick = {
                reg.isLoading = true
                uri1?.let { reg.UploadImg(it,"pfp") }
                uri2?.let { reg.UploadImg(it,"") }
                reg.CreateAcc(ctx, navController)
            },
                modifier = Modifier.width(400.dp),
                shape = RoundedCornerShape(7.dp)) {
                if(reg.isLoading){
                    CircularProgressIndicator(modifier = Modifier.size(20.dp)
                        , color = Color.White)
                }else {
                    Text(text = "Sign up")
                }
            }
            Row {
                Text(text = "Have an Account? ")
                Text(text = "Login.",
                    modifier = Modifier.clickable { navController.navigate(Navigate.Login.name) })
            }
            }


        }
    }
