package mobile_1.wsmb2024.kongsikereta.ViewModel

data class User(
    val email: String = "",
    val password: String = "",
    val ic: String = "",
    val name: String = "",
    val isMale: Boolean = false,
    val userImg: String = "",
    val phone: String = "",
    val address: String = "",
    val isDriver: Boolean = true,
    val car: Car = Car()
)
