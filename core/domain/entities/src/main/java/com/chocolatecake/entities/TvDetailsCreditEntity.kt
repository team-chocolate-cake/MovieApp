package com.chocolatecake.entities

data class TvDetailsCreditEntity(
    val cast:List<Cast>
){
    data class Cast(
        val id: Int,
        val name: String,
        val originalName: String,
        val profileImageUrl:String,
        val character:String
    )
}
