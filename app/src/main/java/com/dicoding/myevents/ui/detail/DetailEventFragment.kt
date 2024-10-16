package com.dicoding.myevents.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.myevents.api.ApiConfig
import com.dicoding.myevents.databinding.FragmentEventDetailBinding
import com.dicoding.myevents.model.DetailResponse
import com.dicoding.myevents.model.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEventFragment : Fragment() {
    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = arguments?.getString("eventId") // Ambil argumen dari Bundle
        if (eventId != null) {
            loadEventDetails(eventId)
        }
    }

    private fun loadEventDetails(eventId: String) {
        binding.progressBar.visibility = View.VISIBLE // Tampilkan indikator loading
        val apiService = ApiConfig.getService()
        apiService.getEventById(eventId.toInt()).enqueue(object : Callback<DetailResponse> {  // Panggil getEventById
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                binding.progressBar.visibility = View.GONE // Sembunyikan indikator loading
                if (response.isSuccessful) {
                    val eventDetail = response.body()?.event
                    // Bind data ke UI
                    binding.name.text = eventDetail?.name
                    binding.description.text = eventDetail?.description
                    // Bind data lainnya...
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {  // Perbaiki tipe di sini
                binding.progressBar.visibility = View.GONE // Sembunyikan indikator loading
                // Tampilkan pesan kesalahan
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to avoid memory leaks
    }
}
