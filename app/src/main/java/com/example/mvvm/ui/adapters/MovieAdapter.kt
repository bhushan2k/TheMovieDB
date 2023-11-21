package com.example.mvvm.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mvvm.GlideApp
import com.example.mvvm.R
import com.example.mvvm.models.Movie

class MovieAdapter(val list: List<Movie>, val callback: MovieClick): RecyclerView.Adapter<ViewHolder>() {

    private val TAG = this::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]
        holder.title.text = movie.title

        val poster = "https://image.tmdb.org/t/p/w500" + movie.poster_path
        Log.d(TAG, "poster path: $poster")

        GlideApp.with(holder.thumbnail.context)
            .load(poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.thumbnail)

        holder.itemView.rootView.setOnClickListener {
            callback.onMovieClick(movie.id.toString())
        }
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    lateinit var thumbnail: ImageView
    lateinit var title: TextView

    init {
        thumbnail = itemView.findViewById(R.id.thumbnail)
        title = itemView.findViewById(R.id.titleText)
    }
}

interface MovieClick {
    fun onMovieClick(id: String)
}