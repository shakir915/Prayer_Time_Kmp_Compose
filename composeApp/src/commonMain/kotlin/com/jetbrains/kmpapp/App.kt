package com.jetbrains.kmpapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.jetbrains.kmpapp.di.locate
import com.jetbrains.kmpapp.di.onLocationUpdateCompose
import com.jetbrains.kmpapp.di.requestLocation
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.offsetIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration


@Composable
fun App() {

    val arrayList = arrayListOf<String>()

    var refresher by remember {
        mutableStateOf(0)
    }



    LaunchedEffect("") {

        onLocationUpdateCompose={
            arrayList.clear()
            arrayList.addAll(getAthanObj(11.0, 76.0).getAthanOfDate(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())).map {

                val format = LocalDateTime.Format { byUnicodePattern("yyyy-MM-dd HH:mm:ss") }
                format.format(it)
            })
            refresher++
        }






    }
    MaterialTheme {
        refresher.let {
            Column {
                arrayList.forEach {
                    Text(it, color = Color.Red)
                }

                Text("Request Location", modifier = Modifier.clickable {
                    requestLocation()
                })

            }
        }

        //Navigator(ListScreen)
    }
}


fun getAthanObj(latitude: Double, longitude: Double): Azan {


    val instant = Clock.System.now()

    val offset = instant.offsetIn(TimeZone.currentSystemDefault())
//
//    val defaultZone = TimeZone.currentSystemDefault().
//
//    val defaultTimeZone: TimeZone = TimeZone.currentSystemDefault()
//    var dstOffsetInHours: Int = 0
//    dstOffsetInHours = if (defaultTimeZone.id) {
//        val dstOffsetInMillis = defaultTimeZone.dstSavings
//        TimeUnit.MILLISECONDS.toHours(dstOffsetInMillis.toLong()).toInt()
//    } else {
//        0
//    }
//
    var tm = spInt("timeMethods", 0) //todo
//
//
//    val gmtOffsetInMillis = defaultTimeZone.getRawOffset() / (3600000.0)
//    val calendar = Calendar.getInstance(defaultTimeZone)
//    //val isDST = defaultTimeZone.inDaylightTime(calendar.getTime())

    println("offset ${offset.totalSeconds}")
    val dstOffsetInHours = 0 //todo
    val offsetINHour= offset.totalSeconds.div(60*60.0)

    println("latitude $latitude longitude $longitude offsetINHour ${offsetINHour} dstOffsetInHours ${dstOffsetInHours} ")

    val location = Location(latitude, longitude, offsetINHour, dstOffsetInHours)
    val azan = Azan(location, timeMethods[tm].first)
    return azan
}


//fun createInstant(year: Int, month: Int, date: Int, hour: Int, minute: Int, second: Int): Instant {
//    val localDateTime = LocalDate(year, month, date).atTime(hour, minute, second)
//    return localDateTime.toInstant(TimeZone.UTC) // Adjust TimeZone if needed
//}

fun Azan.getAthanOfDate(today: LocalDateTime): List<LocalDateTime> {
    return getPrayerTimes(SimpleDate(24,7,2024)).times.mapIndexed { index, it ->
        val adj = spInt("adjustment_$index", if (index == 2 || index == 3) 1 else 0)
        println("prayerTimesMILLLI $it")
       var a= LocalDateTime(today.year, today.monthNumber, today.dayOfMonth, it.hour, it.minute, it.second)
            .toInstant(TimeZone.currentSystemDefault())
            .plus(Duration.parse("${adj}m")).toLocalDateTime(TimeZone.currentSystemDefault())
        a

    }
}

val timeMethods: Array<Triple<Method, String, String>> = arrayOf(

    Triple(
        Method.KARACHI_SHAF, "University of Islamic Sciences, Karachi (Shaf'i)",
        "University of Islamic Sciences, Karachi (Shaf'i)\n" +
                "Fajr Angle      = 18\n" +
                "Ishaa Angle = 18\n" +
                "Used in: Iran, Kuwait, parts of Europe",
    ),
    Triple(
        Method.KARACHI_HANAF, "University of Islamic Sciences, Karachi (Hanafi)", "University of Islamic Sciences, Karachi (Hanafi)\n" +
                "Fajr Angle = 18\n" +
                "Ishaa Angle = 18\n" +
                "Used in: Afghanistan, Bangladesh, India"
    ),

    Triple(
        Method.EGYPT_SURVEY, "Egyptian General Authority of Survey", "Egyptian General Authority of Survey\n" +
                "Fajr Angle = 20\n" +
                "Ishaa Angle = 18\n" +
                "Used in: Indonesia, Iraq, Jordan, Lebanon, Malaysia, Singapore, Syria, parts of Africa, parts of United States"
    ),

    Triple(
        Method.NORTH_AMERICA, "Islamic Society of North America",
        "Islamic Society of North America\n" +
                "Fajr Angle = 15\n" +
                "Ishaa Angle = 15\n" +
                "Used in: Canada, Parts of UK, parts of United States",
    ),
    Triple(
        Method.MUSLIM_LEAGUE, "Muslim World League (MWL)", "Muslim World League (MWL)\n" +
                "Fajr Angle = 18\n" +
                "Ishaa Angle = 17\n" +
                "Used in: parts of Europe, Far East, parts of United States"
    ),
    Triple(
        Method.UMM_ALQURRA, "Om Al-Qurra University",
        "Om Al-Qurra University\n" +
                "Fajr Angle = 19\n" +
                "Ishaa Interval = 90 minutes from Al-Maghrib prayer but set to 120 during Ramadan.\n" +
                "Used in: Saudi Arabia",
    ),

    Triple(
        Method.FIXED_ISHAA, "Fixed Ishaa Angle Interval (always 90)",
        "Fixed Ishaa Angle Interval (always 90)\n" +
                "Fajr Angle = 19.5\n" +
                "Ishaa Interval = 90 minutes from Al-Maghrib prayer.\n" +
                "Used in: Bahrain, Oman, Qatar, United Arab Emirates",
    ),


    )

//todo
fun spInt(k: String, i: Int): Int {
    return 0;
}






