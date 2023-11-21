package com.example.mvvm.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.ui.adapters.MovieAdapter
import com.example.mvvm.ui.adapters.MovieClick
import com.example.mvvm.R
import com.example.mvvm.utils.GridSpacingItemDecoration
import com.example.mvvm.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieClick {

    private val TAG = this::class.java.simpleName

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getMovies()

        mainViewModel.moviesLiveData.observe(this) {

            val movieAdapter = MovieAdapter(it, this)

            val gridLayoutManager = GridLayoutManager(this, 2)

            val spanCount = 2 // 2 columns
            val spacing = 5 // 5px
            val includeEdge = false

            recyclerView.apply {
                adapter = movieAdapter
                layoutManager = gridLayoutManager
                addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
                itemAnimator = DefaultItemAnimator()
            }
        }
    }

    override fun onMovieClick(id: String) {
        Log.d("MainActivity", "movie clicked $id")
        val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java).apply {
            putExtra("movie_id", id)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}