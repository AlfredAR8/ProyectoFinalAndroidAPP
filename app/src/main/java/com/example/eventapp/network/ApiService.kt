// ApiService.kt
package com.example.eventapp.network

import com.example.eventapp.model.Event
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("events/{id}")
    suspend fun getEventById(@Path("id") id: Int): Response<Event>

    @POST("events")
    suspend fun createEvent(
        @Header("Authorization") token: String,
        @Body eventCreateRequest: EventCreateRequest
    ): Response<Event>

    @PUT("events/{id}")
    suspend fun updateEvent(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body eventUpdateRequest: EventUpdateRequest
    ): Response<Event>

    @DELETE("events/{id}")
    suspend fun deleteEvent(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeleteResponse>

    @POST("events/{id}/respond")
    suspend fun respondToEvent(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body respondRequest: RespondRequest
    ): Response<Event>

    companion object {
        private const val BASE_URL = "http://127.0.0.1:8000"

        fun create(): ApiService {
            val logger = okhttp3.logging.HttpLoggingInterceptor().apply {
                level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            }

            val client = okhttp3.OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}