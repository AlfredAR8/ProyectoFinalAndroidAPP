// DeleteResponse.kt
package com.example.eventapp.network

import com.google.gson.annotations.SerializedName

data class DeleteResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String
)