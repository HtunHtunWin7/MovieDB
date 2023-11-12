package com.example.moviedb.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.databinding.MovieItemBinding
import com.example.moviedb.model.Movie

class MovieAdapter(private val clickAction: ClickAction) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {
    private val imgPath = "https://image.tmdb.org/t/p/w500/"

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(movie: Movie) {
            Glide.with(binding.root.context).load(imgPath + movie.poster_path)
                .into(binding.imgMovie)
            binding.txtMovieTitle.text = movie.title
            binding.txtDateTime.text = movie.release_date
            binding.txtOriginalTitle.text = movie.overview
            binding.root.setOnClickListener {
                clickAction.onClick(movie.id)
            }
            if (movie.favorite == 1) {
                binding.btnFavorite.background =
                    binding.root.context.getDrawable(R.drawable.baseline_favorite_checked_24)
            }else{
                binding.btnFavorite.background =
                    binding.root.context.getDrawable(R.drawable.baseline_favorite_border_24)
            }
            binding.btnFavorite.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.btnFavorite.background =
                        binding.root.context.getDrawable(R.drawable.baseline_favorite_checked_24)
                    val _movie = Movie(
                        pk = movie.pk,
                        id = movie.id,
                        title = movie.title,
                        poster_path = movie.poster_path,
                        vote_average = movie.vote_average,
                        overview = movie.overview,
                        release_date = movie.release_date,
                        popularity = movie.popularity,
                        vote_count = movie.vote_count,
                        video = movie.video,
                        original_language = movie.original_language,
                        original_title = movie.original_title,
                        adult = movie.adult,
                        favorite = 1
                    )
                    clickAction.updateMovie(_movie)
                } else {
                    binding.btnFavorite.background =
                        binding.root.context.getDrawable(R.drawable.baseline_favorite_border_24)
                    val _movie = Movie(
                        pk = movie.pk,
                        id = movie.id,
                        title = movie.title,
                        poster_path = movie.poster_path,
                        vote_average = movie.vote_average,
                        overview = movie.overview,
                        release_date = movie.release_date,
                        popularity = movie.popularity,
                        vote_count = movie.vote_count,
                        video = movie.video,
                        original_language = movie.original_language,
                        original_title = movie.original_title,
                        adult = movie.adult,
                        favorite = 0
                    )
                    clickAction.updateMovie(_movie)
                }
            }

        }

    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    interface ClickAction {
        fun onClick(id: Int)
        fun updateMovie(movie: Movie)
    }
}