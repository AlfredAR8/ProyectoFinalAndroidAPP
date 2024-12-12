// EventRepository.kt
package com.example.eventapp.repository

import com.example.eventapp.model.Event
import com.example.eventapp.network.*
import retrofit2.Response

class EventRepository {

    private val apiService = ApiService.create()

    suspend fun getEvents(): Response<List<Event>> {
        return apiService.getEvents()
    }

    suspend fun getEventById(id: Int): Response<Event> {
        return apiService.getEventById(id)
    }

    suspend fun createEvent(token: String, eventRequest: EventCreateRequest): Response<Event> {
        return apiService.createEvent("Bearer $token", eventRequest)
    }

    suspend fun updateEvent(token: String, id: Int, eventRequest: EventUpdateRequest): Response<Event> {
        return apiService.updateEvent("Bearer $token", id, eventRequest)
    }

    suspend fun deleteEvent(token: String, id: Int): Response<DeleteResponse> {
        return apiService.deleteEvent("Bearer $token", id)
    }

    suspend fun respondToEvent(token: String, id: Int, respondRequest: RespondRequest): Response<Event> {
        return apiService.respondToEvent("Bearer $token", id, respondRequest)
    }
}