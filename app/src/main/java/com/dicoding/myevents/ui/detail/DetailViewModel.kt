package com.dicoding.myevents.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myevents.model.DetailResponse

class DetailEventViewModel : ViewModel() {
    private val _event = MutableLiveData<DetailResponse>()
    val event: LiveData<DetailResponse> get() = _event

    fun setEvent(eventItem: DetailResponse) {
        _event.value = eventItem
    }
}
