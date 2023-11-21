package com.example.mvvm.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mvvm.GlideApp
import com.example.mvvm.R
import com.example.mvvm.utils.Constants.dateConvert
import com.example.mvvm.utils.Constants.duration
import com.example.mvvm.utils.Constants.voteConvert
import com.example.mvvm.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    private lateinit var mainViewModel: MainViewModel

    private val titleText: TextView
        get() = findViewById(R.id.titleText)

    private val infoText: TextView
        get() = findViewById(R.id.infoText)

    private val languageText: TextView
        get() = findViewById(R.id.languageText)

    private val overviewText: TextView
        get() = findViewById(R.id.overviewText)

    private val voteText: TextView
        get() = findViewById(R.id.voteText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val toolbar =findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (intent != null) {
            val id = intent.getStringExtra("movie_id")
            Log.d("Movie Detail", "id: $id")

            mainViewModel.getMovieDetails(id!!)
        }

        mainViewModel.movieDetailLiveData.observe(this) {
            Log.d(TAG, "movie detail title: ${it.original_title}")
            Log.d(TAG, "movie detail overview: ${it.overview}")

            titleText.text = it.title

            val poster = "https://image.tmdb.org/t/p/w500" + it.poster_path

            GlideApp.with(this)
                .load(poster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(findViewById(R.id.thumbnail))

            val duration = duration(it.runtime)

            val genre = it.genres.joinToString { x -> x.name }

            languageText.text = it.spoken_languages.joinToString { x -> x.name }

            infoText.text = "$duration · $genre · ${dateConvert(it.release_date)}"

            overviewText.text = it.overview

            voteText.text = "${voteConvert(it.vote_average)}/10 (${it.vote_count} Votes)"
        }


    }
}