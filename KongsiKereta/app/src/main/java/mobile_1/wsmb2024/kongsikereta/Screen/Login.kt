package mobile_1.wsmb2024.kongsikereta.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM
import org.w3c.dom.Text

@Composable
fun Login(navController: NavController,login:DriverVM = viewModel()){
    val ctx = LocalContext.current
    Column{
        Column(modifier = Modifier.padding(24.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(value = login.email, onValueChange = {login.email = it})
            OutlinedTextField(value = login.password, onValueChange = {login.password = it})
            Button(onClick = { login.Login(ctx, navController) }) {
                Text(text = "Log in")
            }
            Row {
                Text(text = "New user? ")
                Text(text = "Sign up. ",
                    modifier = Modifier.clickable { navController.navigate(Navigate.Register.name)  })
            }
        }
    }
}