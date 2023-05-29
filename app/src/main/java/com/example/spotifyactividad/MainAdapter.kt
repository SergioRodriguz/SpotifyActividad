package com.example.spotifyactividad


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainAdapter(
    private val dataSet: List<PlaylistResponse.Playlist>,
    private val onItemClick: (PlaylistResponse.Playlist) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista, viewGroup, false)

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val playlist = dataSet[position]
        viewHolder.bind(playlist)
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageList: ImageView = view.findViewById(R.id.image)
        private val titleList: TextView = view.findViewById(R.id.nombreLista)
        private val followersList: TextView = view.findViewById(R.id.seguidores)


        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val playlist = dataSet[position]
                    onItemClick.invoke(playlist)

                }
            }
        }

        fun bind(playlist: PlaylistResponse.Playlist) {
            titleList.text = playlist.nameOfSong
            followersList.text = "${playlist.numberOfFollowers} SEGUIDORES"


            Picasso.get().load(playlist.dummyImageUrl).into(imageList)
        }



    }


}