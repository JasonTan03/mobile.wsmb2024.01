package mobile_1.wsmb2024.kongsikereta.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Edit(){
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .height(100.dp),
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
                .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "test")
            }
        }
    ) {
        Column (modifier = Modifier.padding(it)){
            Column(modifier = Modifier.padding(24.dp)) {

            }
        }
    }
}