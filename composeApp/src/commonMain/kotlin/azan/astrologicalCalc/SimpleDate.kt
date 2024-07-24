package com.azan.astrologicalCalc

import kotlinx.datetime.LocalDateTime


class SimpleDate {
    var day: Int = 0

    var month: Int = 0

    var year: Int = 0

    /**
     * TODO
     * @param day
     * @param month
     * @param year
     */
    constructor(day: Int, month: Int, year: Int) {
        this.day = day
        this.month = month
        this.year = year
    }

    /**
     * TODO
     * @param gCalendar
     */
    constructor(gCalendar: LocalDateTime) {
        this.day = gCalendar.dayOfMonth
        this.month = gCalendar.monthNumber
        this.year = gCalendar.year
    }

    fun copy(): SimpleDate {
        return SimpleDate(day, month, year)
    }

}
