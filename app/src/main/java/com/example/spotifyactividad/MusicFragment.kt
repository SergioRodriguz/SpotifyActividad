package com.example.spotifyactividad

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MusicFragment : Fragment() {

    private lateinit var musicRecyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)

        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Fresco"
            val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT)
            layoutParams.gravity = Gravity.CENTER
            customView?.layoutParams = layoutParams
            displayOptions = displayOptions or ActionBar.DISPLAY_SHOW_CUSTOM
        }

        musicRecyclerView = view.findViewById(R.id.musicRecicleView)

        val playlist = arguments?.getSerializable("playlist") as? PlaylistResponse.Playlist

        playlist?.let {
            (activity as AppCompatActivity).supportActionBar?.apply {
                title = it.nameOfSong
            }
            val musicAdapter = MusicAdapter(it.songs)
            musicRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            musicRecyclerView.adapter = musicAdapter
        }

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed() // Volver atrás al presionar el botón de retroceso
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}