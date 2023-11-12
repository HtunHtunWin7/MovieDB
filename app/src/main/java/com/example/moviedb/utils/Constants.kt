package com.example.moviedb.utils

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "11d4fbee2af9f6b42f5fcec50cc2e357"
}

fun AppCompatActivity.showSnackBar(message: String) {
    Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
}