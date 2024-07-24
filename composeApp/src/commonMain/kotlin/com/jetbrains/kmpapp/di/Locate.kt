package com.jetbrains.kmpapp.di


var requestLocation = {

}

//var locate : (latitude: Double,longitude: Double)->Unit = {a,b
//
//}
var onLocationUpdateCompose = {

}

var latitude = 11.0
var longitude = 76.0
fun locate(lt: Double, lg: Double) {
    latitude = lt
    latitude = lg
    onLocationUpdateCompose?.invoke()
}