package com.example.spotifyactividad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MusicAdapter(private val dataSet: List<PlaylistResponse.Playlist.Song>) :
    RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageMusic: ImageView = view.findViewById(R.id.musicImage)
        private val titleSong: TextView = view.findViewById(R.id.titleMusic)
        private val nameArtist: TextView = view.findViewById(R.id.nameSinger)

        fun bind(song: PlaylistResponse.Playlist.Song) {
            nameArtist.text = song.artist
            titleSong.text = song.name


            Picasso.get().load(song.url).into(imageMusic)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cancion, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val song = dataSet[position]
        viewHolder.bind(song)
    }


}
