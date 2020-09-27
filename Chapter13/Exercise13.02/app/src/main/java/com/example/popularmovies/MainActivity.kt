package com.example.popularmovies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()

    private val movieAdapter by lazy {
        MovieAdapter(movies, object : MovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list.adapter = movieAdapter

        getMovies()
    }

    private fun getMovies() {
        val movieService = (application as MovieApplication).movieService

        val movieViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieViewModel(movieService) as T
            }
        }).get(MovieViewModel::class.java)

        movieViewModel.fetchPopularMovies()
        movieViewModel.getPopularMovies()
            .observe(this, Observer { popularMovies ->
                movies.addAll(popularMovies)
                movieAdapter.notifyDataSetChanged()
            })
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }

}