package mobile_1.wsmb2024.kongsikereta.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SelectVM: ViewModel() {
    var rideID by mutableStateOf("")
}