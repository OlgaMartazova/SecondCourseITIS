package com.itis.secondcourseitis.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.secondcourseitis.R
import com.itis.secondcourseitis.SpaceItemDecorator
import com.itis.secondcourseitis.databinding.FragmentTrackListBinding
import com.itis.secondcourseitis.recyclerview.TrackAdapter
import com.itis.secondcourseitis.repository.TrackRepository

class TracksFragment: Fragment() {
    private lateinit var binding: FragmentTrackListBinding
    private lateinit var trackAdapter: TrackAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        trackAdapter = TrackAdapter(TrackRepository.tracks) {
            showSelectedTrack(it)
        }
        binding.rvTracks.run {
            adapter = trackAdapter
            addItemDecoration(decorator)
            addItemDecoration(spacing)
        }
    }

    private fun showSelectedTrack(idSong: Int) {
        val bundle = Bundle().apply {
            putInt("idSong", idSong)
        }
        findNavController().navigate(R.id.action_fragment_list_to_fragment_detail, bundle)
    }
}
