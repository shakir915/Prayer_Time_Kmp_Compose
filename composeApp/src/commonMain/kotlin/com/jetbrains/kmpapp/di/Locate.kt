package com.jetbrains.kmpapp.di


var requestLocation = {

}

//var locate : (latitude: Double,longitude: Double)->Unit = {a,b
//
//}
var onLocationUpdateCompose = {

}



const val latitude_KOTTAKKAL = 11.0
var longitude_KOTTAKKAL = 76.0


var latitude =latitude_KOTTAKKAL
var longitude = longitude_KOTTAKKAL
var placeName = "Location"
fun locate(lt: Double, lg: Double,pn:String) {
    placeName=pn
    latitude = lt
    longitude = lg
    onLocationUpdateCompose?.invoke()
}