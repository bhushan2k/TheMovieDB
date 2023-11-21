package com.example.mvvm.utils

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object Constants {
    const val BASE_URL = "http://api.themoviedb.org/3/"
    const val API_KEY = "06959e922c10c65244e35ddfe81850e5"

    val duration = {
        totalMinutes: Int ->
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        "${hours}h ${minutes}m"
    }

    @SuppressLint("SimpleDateFormat")
    public fun dateConvert(time: String) : String{
        var inputPattern = ""
        if (!time.contains("-")){
            return time
        }
        inputPattern = "yyyy-MM-dd"

        val outputPattern = "dd MMM, yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)

        var date: Date? = null
        var str: String? = null

        try {
            date = inputFormat.parse(time)
            str = date?.let { outputFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str!!
    }

    val voteConvert = {
        vote: Double ->
        val df = DecimalFormat("#.#")
        df.format(vote)
    }
}