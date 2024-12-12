// RespondRequest.kt
package com.example.eventapp.network

import com.google.gson.annotations.SerializedName

data class RespondRequest(
    @SerializedName("response") val response: String
)