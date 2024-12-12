package com.example.eventapp.network

import com.google.gson.annotations.SerializedName

data class EventCreateRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: String, // Formato: "YYYY-MM-DD"
    @SerializedName("time") val time: String, // Formato: "HH:MM"
    @SerializedName("location") val location: String,
    @SerializedName("image") val image: String? = null // Opcional
)