package com.dicoding.myevents.ui.Upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myevents.api.ApiConfig
import com.dicoding.myevents.model.EventResponse
import com.dicoding.myevents.model.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpcomingViewModel : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvents: LiveData<List<ListEventsItem>> = _upcomingEvents

    fun fetchUpcomingEvents() {
        val apiService = ApiConfig.getService()
        apiService.getEvents(active = 1).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    _upcomingEvents.value = response.body()?.listEvents ?: emptyList()
                    Log.d("UpcomingViewModel", "Event berhasil diambil: ${response.body()?.listEvents}")
                } else {
                    Log.d("UpcomingViewModel", "Response tidak berhasil")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.e("UpcomingViewModel", "Gagal memuat event", t)
            }
        })
    }
}
