// EventViewModel.kt
package com.example.eventapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.model.Event
import com.example.eventapp.network.DeleteResponse
import com.example.eventapp.network.EventCreateRequest
import com.example.eventapp.network.EventUpdateRequest
import com.example.eventapp.network.RespondRequest
import com.example.eventapp.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class EventViewModel : ViewModel() {

    private val repository = EventRepository()

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    private val _eventDetail = MutableStateFlow<Event?>(null)
    val eventDetail: StateFlow<Event?> = _eventDetail

    // Respuesta para crear y actualizar eventos
    private val _eventActionResponse = MutableStateFlow<Response<Event>?>(null)
    val eventActionResponse: StateFlow<Response<Event>?> = _eventActionResponse

    // Respuesta para eliminar eventos
    private val _deleteActionResponse = MutableStateFlow<Response<DeleteResponse>?>(null)
    val deleteActionResponse: StateFlow<Response<DeleteResponse>?> = _deleteActionResponse

    // Respuesta para responder a eventos
    private val _respondActionResponse = MutableStateFlow<Response<Event>?>(null)
    val respondActionResponse: StateFlow<Response<Event>?> = _respondActionResponse

    fun fetchEvents() {
        viewModelScope.launch {
            val response = repository.getEvents()
            if (response.isSuccessful) {
                _events.value = response.body() ?: emptyList()
            }

        }
    }

    fun fetchEventById(id: Int) {
        viewModelScope.launch {
            val response = repository.getEventById(id)
            if (response.isSuccessful) {
                _eventDetail.value = response.body()
            }
        }
    }

    fun createEvent(token: String, eventRequest: EventCreateRequest) {
        viewModelScope.launch {
            val response = repository.createEvent(token, eventRequest)
            _eventActionResponse.value = response
            if (response.isSuccessful) {
                fetchEvents()
            }
        }
    }

    fun updateEvent(token: String, id: Int, eventRequest: EventUpdateRequest) {
        viewModelScope.launch {
            val response = repository.updateEvent(token, id, eventRequest)
            _eventActionResponse.value = response
            if (response.isSuccessful) {
                fetchEventById(id)
            }
        }
    }

    fun deleteEvent(token: String, id: Int) {
        viewModelScope.launch {
            val response = repository.deleteEvent(token, id)
            _deleteActionResponse.value = response
            if (response.isSuccessful) {
                fetchEvents()
            }
        }
    }

    fun respondToEvent(token: String, id: Int, respondRequest: RespondRequest) {
        viewModelScope.launch {
            val response = repository.respondToEvent(token, id, respondRequest)
            _respondActionResponse.value = response
            if (response.isSuccessful) {
                fetchEventById(id)
            }
        }
    }
}