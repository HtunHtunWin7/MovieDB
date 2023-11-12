package com.example.moviedb.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentDetailBinding
import com.example.moviedb.model.Movie
import com.example.moviedb.state.ViewState
import com.example.moviedb.utils.showSnackBar
import com.example.moviedb.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val imgPath = "https://image.tmdb.org/t/p/w500/"
    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val id = args.myArg
        viewModel.getMovieById(id)
        setSubscribeUI()
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setSubscribeUI() {
        viewModel.movieById.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    val movie = it.value!!
                    Glide.with(requireContext()).load(imgPath + movie.poster_path)
                        .into(binding.imgMovie)
                    binding.txtTitle.text = movie.title
                    binding.txtOverview.text = movie.overview
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
                            viewModel.updateMovie(_movie)
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
                            viewModel.updateMovie(_movie)
                        }
                    }
                }
                is ViewState.Error -> { Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_SHORT).show()}
                is ViewState.Loading -> {}
            }
        }
    }


}