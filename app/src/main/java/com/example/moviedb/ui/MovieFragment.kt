package com.example.moviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.R
import com.example.moviedb.adapter.MovieAdapter
import com.example.moviedb.adapter.MovieLoadingStateAdapter
import com.example.moviedb.databinding.FragmentMovieBinding
import com.example.moviedb.model.Movie
import com.example.moviedb.state.ViewState
import com.example.moviedb.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.ClickAction {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        viewModel.getMovies()
        setUpViews()
        setSubscribeUI()
        binding.swipeRefreshLayout.setOnRefreshListener {
            movieAdapter.refresh()
        }
        binding.btnRetry.setOnClickListener {
            movieAdapter.refresh()
        }
        return binding.root
    }

    private fun setSubscribeUI() {
        viewModel.movie.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it.value!!)
                    }
                }

                is ViewState.Error -> {
                    Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_SHORT).show()
                }

                is ViewState.Loading -> {}
            }
        }
    }

    private fun errorVieHide() {
        binding.txtError.visibility = View.GONE
        binding.btnRetry.visibility = View.GONE
    }

    private fun errorView() {
        binding.txtError.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun setUpViews() {
        movieAdapter = MovieAdapter(this)

        binding.recyclerMovie.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadingStateAdapter { movieAdapter.retry() }
            )
        }
        movieAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (movieAdapter.snapshot().isEmpty()) {
                    binding.progressBar.isVisible = true
                }
                //binding.errorTxt.isVisible = false
            } else {
                binding.progressBar.isVisible = false
                binding.swipeRefreshLayout.isRefreshing = false
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    if (movieAdapter.snapshot().isEmpty()) {
                        /* binding.errorTxt.isVisible = true
                         binding.errorTxt.text = it.error.localizedMessage*/
                    }
                }
            }
        }
    }

    override fun onClick(id: Int) {
        findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(id))
    }


    override fun updateMovie(movie: Movie) {
        viewModel.updateMovie(movie)
    }

}