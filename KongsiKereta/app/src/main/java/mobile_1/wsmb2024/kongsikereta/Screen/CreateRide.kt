package mobile_1.wsmb2024.kongsikereta.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import mobile_1.wsmb2024.kongsikereta.Navigate
import mobile_1.wsmb2024.kongsikereta.ViewModel.DriverVM
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRide(navController: NavController, create: DriverVM = viewModel()){
    val ctx = LocalContext.current
    val auth = Firebase.auth
    val userID = auth.currentUser?.uid
    if(userID != null){
        LaunchedEffect(userID) {
            create.getUserData(userID)
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
                Text(text = "Create Ride")
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
                IconButton(onClick = {navController.navigate(Navigate.Profile.name) }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile")
                }
                IconButton(onClick = { navController.navigate(Navigate.Home.name) }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "home")
                }
                IconButton(onClick = { navController.navigate(Navigate.CreateRide.name) }) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "add ride")

                }
            }
        }
    ) {
        Column (modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())){
            Column(modifier = Modifier.padding(24.dp)) {
                var showDatePicker by remember { mutableStateOf(false) }
                val datePickerState = rememberDatePickerState()
                val selectedDate = datePickerState.selectedDateMillis?.let {
                    convertMillisToDate(it, create)
                } ?: ""
                OutlinedTextField(
                    value = create.destination,
                    onValueChange = {create.destination = it},
                    singleLine = true,
                    isError = false)

                OutlinedTextField(
                    value = create.origin,
                    onValueChange = {create.origin = it},
                    singleLine = true,
                    isError = false)

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { },
                    label = { Text("DOB") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = !showDatePicker }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select date"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                )

                if (showDatePicker) {
                    AlertDialog(
                        onDismissRequest = { showDatePicker = false },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 64.dp)
                                .shadow(elevation = 4.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(16.dp)
                        ) {
                            DatePicker(
                                state = datePickerState,
                                showModeToggle = false
                            )
                        }
                    }
                }

                var showDial by remember{
                    mutableStateOf(false)
                }
                var selectedTime: TimePickerState? by remember { mutableStateOf(null) }
                val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                Button(onClick = { showDial = true }) {

                }
                if(showDial) {
                    DialUseStateExample(
                        onDismiss = {
                            showDial = false
                        },
                        onConfirm = { time ->
                            selectedTime = time
                            showDial = false
                        },
                    )
                }
                val cal = Calendar.getInstance()
                if(selectedTime != null) {

                    cal.set(Calendar.HOUR_OF_DAY, selectedTime!!.hour)
                    cal.set(Calendar.MINUTE, selectedTime!!.minute)
                    cal.isLenient = false
                    Text("Selected time = ${formatter.format(cal.time)}")
                }

                OutlinedTextField(
                    value = formatter.format(cal.time),
                    onValueChange = {},
                    singleLine = true,
                    isError = false,
                    readOnly = true,)

                OutlinedTextField(
                    value = create.tempfare,
                    onValueChange = {create.tempfare = it},
                    singleLine = true,
                    isError = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Button(onClick = { create.CreateRide(ctx) }) {
                    Text(text = "Create Ride")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialUseStateExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = { onConfirm(timePickerState) }) {
            Text("Confirm selection")
        }
    }
}

fun convertMillisToDate(millis: Long, create: DriverVM): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    create.date = formatter.format(Date(millis))
    return formatter.format(Date(millis))
}