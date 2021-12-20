package com.example.notes.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: Int,
    val name: String,
    val description: String
) : Parcelable