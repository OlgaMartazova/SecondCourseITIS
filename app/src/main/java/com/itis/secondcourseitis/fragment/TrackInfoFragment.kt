package com.itis.secondcourseitis.fragment

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.secondcourseitis.R
import com.itis.secondcourseitis.databinding.FragmentTrackDetailsBinding
import com.itis.secondcourseitis.model.Track
import com.itis.secondcourseitis.repository.TrackRepository
import com.itis.secondcourseitis.service.MusicService

class TrackInfoFragment : Fragment() {
    private lateinit var binding: FragmentTrackDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var binder: MusicService.MusicBinder? = null
    private var musicService: MusicService? = null
    private lateinit var track: Track

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binder = service as? MusicService.MusicBinder
            musicService = binder?.getService()
            connectTrack()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
            binder = null
        }
    }

    private fun connectTrack() {
        val idSong = arguments?.getInt("idSong")
        idSong?.let {
            setData(idSong)
            playTrack(idSong)
        }
    }

    private fun playTrack(idSong: Int) {
        musicService?.currentTrack(idSong)

        with(binding) {
            ibPrev.setOnClickListener {
                musicService?.previousTrack()
                setData(musicService?.currentPosition)
                musicService?.play()
                ibPausePlay.setBackgroundResource(R.drawable.ic_baseline_pause_24)
            }
            ibNext.setOnClickListener {
                musicService?.nextTrack()
                setData(musicService?.currentPosition)
                musicService?.play()
                ibPausePlay.setBackgroundResource(R.drawable.ic_baseline_pause_24)
            }
            ibPausePlay.setOnClickListener {
                if (musicService?.isPlay() == true) {
                    musicService?.pause()
                    ibPausePlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
                }
                else {
                    musicService?.play()
                    ibPausePlay.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                }
            }
        }
    }

    private fun setData(id: Int?) {
        id?.let{
            track = TrackRepository.tracks[id]
        }
        with(binding) {
            tvTitle.text = track.title
            tvAuthor.text = track.author
            ivCover.setImageResource(track.cover)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.bindService(
            Intent(context, MusicService::class.java),
            connection,
            BIND_AUTO_CREATE
        )
    }
}
