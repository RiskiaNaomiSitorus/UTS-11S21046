package com.ifs21046.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Group(
    val name: String,
    val icon: Int,
    val description: String,
    val perilakudanklasifikasi: String,
    val periodehidup: String,
    val habitatdanlingkungan: String,
) : Parcelable