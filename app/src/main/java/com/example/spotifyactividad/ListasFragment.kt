package com.example.spotifyactividad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Serializable


class ListasFragment : Fragment() {

    private lateinit var listaRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listas, container, false)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.custom_action_bar)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaRecyclerView = view.findViewById(R.id.listasRecyclerView)

        val json = readJsonFromFile("playlist.json")
        val playlist = Gson().fromJson(json, PlaylistResponse::class.java)
        Log.i("Buscar", playlist.data.toString())

        val miAdapter = MainAdapter(playlist.data) { playlist ->

            Log.d("Buscar", "Se hizo clic en la playlist: $playlist")

            val intent = Intent(requireActivity(), MusicAdapter::class.java)
            intent.putExtra("playlist", playlist as Serializable)
            requireActivity().startActivity(intent)
        }



        listaRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        listaRecyclerView.adapter = miAdapter
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(requireContext().assets.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }

            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }


}