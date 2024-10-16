package com.dicoding.myevents.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myevents.databinding.FragmentFinishedBinding
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myevents.R
import com.dicoding.myevents.ui.EventAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

class FinishedFragment : Fragment() {
    private lateinit var viewModel: FinishedViewModel
    private lateinit var binding: FragmentFinishedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel menggunakan ViewModelProvider
        viewModel = ViewModelProvider(this)[FinishedViewModel::class.java]

        // Observe LiveData
        viewModel.finishedEvents.observe(viewLifecycleOwner) { events ->
            // Update UI dengan data dari ViewModel
        }

        // Panggil method untuk load data
        viewModel.loadFinishedEvents()
    }
}

