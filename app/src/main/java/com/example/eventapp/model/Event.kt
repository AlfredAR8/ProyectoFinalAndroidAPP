// Event.kt
package com.example.eventapp.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: String, // Formato: "YYYY-MM-DD"
    @SerializedName("time") val time: String, // Formato: "HH:MM"
    @SerializedName("location") val location: String,
    @SerializedName("image") val image: String?, // URL de la imagen (opcional)
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String
)