// EventUpdateRequest.kt
package com.example.eventapp.network

import com.google.gson.annotations.SerializedName

data class EventUpdateRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("date") val date: String? = null, // Formato: "YYYY-MM-DD"
    @SerializedName("time") val time: String? = null, // Formato: "HH:MM"
    @SerializedName("location") val location: String? = null,
    @SerializedName("image") val image: String? = null // Opcional
)