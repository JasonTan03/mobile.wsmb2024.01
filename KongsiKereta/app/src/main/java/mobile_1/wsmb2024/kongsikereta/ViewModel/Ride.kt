package mobile_1.wsmb2024.kongsikereta.ViewModel

data class Ride(
    val driverRef: String ="",
    val car: Car = Car(),
    val riderRef: ArrayList<String> = ArrayList(),
    val destination: String ="",
    val origin: String ="",
    val date: String ="",
    val time: String ="",
    val fare: Double = 0.0,
    var rideID: String = ""
    )
