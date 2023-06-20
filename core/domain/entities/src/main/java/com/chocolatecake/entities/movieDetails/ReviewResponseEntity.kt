package com.chocolatecake.entities.movieDetails


data class ReviewResponseEntity(
    val reviews: List<ReviewEntity>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)