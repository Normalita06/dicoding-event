package com.dicoding.myevents.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myevents.R
import com.dicoding.myevents.ui.EventAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myevents.ui.Upcoming.UpcomingViewModel
import com.dicoding.myevents.databinding.FragmentUpcomingBinding


class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UpcomingViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView dan Adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Set LayoutManager sebelum mengatur adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context)  // Set LayoutManager terlebih dahulu

        adapter = EventAdapter(emptyList()) { listEvents ->
            // Handle item click jika ada
        }
        recyclerView.adapter = adapter  // Setelah LayoutManager

        // Observasi data dari ViewModel
        viewModel.upcomingEvents.observe(viewLifecycleOwner) { listEvents ->
            if (listEvents.isNotEmpty()) {
                adapter.updateEvents(listEvents)
            } else {
                // Handle jika event kosong
            }
        }

        // Memulai pemanggilan API untuk event
        viewModel.fetchUpcomingEvents()
    }
}
