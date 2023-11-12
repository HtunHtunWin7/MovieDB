package com.example.moviedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity()
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0,
    @SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("poster_path")
    val poster_path: String?,
    @field:SerializedName("vote_average")
    val vote_average: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("release_date")
    val release_date: String,
    @field:SerializedName("popularity")
    val popularity: Float,
    @field:SerializedName("vote_count")
    val vote_count: Int,
    @field:SerializedName("video")
    val video: Boolean,/*
    @field:SerializedName("backdrop_path")
    val backdrop_path: String,*/
    @field:SerializedName("original_language")
    val original_language: String,
    @field:SerializedName("original_title")
    val original_title: String,
    @field:SerializedName("adult")
    val adult: Boolean,
    var favorite: Int = 0
)