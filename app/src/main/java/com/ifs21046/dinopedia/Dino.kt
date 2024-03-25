package com.ifs21046.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    val name: String,
    val icon: Int,
    val description: String,
    val kelompok: String,
    val habitat: String,
    val makanan: String,
    val panjang: String,
    val tinggi: String,
    val bobot: String,
   val kelemahan: String,
) : Parcelable