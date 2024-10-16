package com.dicoding.myevents.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myevents.api.ApiConfig
import com.dicoding.myevents.model.EventResponse
import com.dicoding.myevents.model.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishedViewModel : ViewModel() {
    private val _finishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> = _finishedEvents

    fun loadFinishedEvents() {
        val apiService = ApiConfig.getService()
        apiService.getEvents(active = 0).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    _finishedEvents.value = response.body()?.listEvents ?: emptyList()
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
