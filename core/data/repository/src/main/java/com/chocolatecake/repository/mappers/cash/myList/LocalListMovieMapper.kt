//package com.chocolatecake.repository.mappers.cash.myList
//
//import com.chocolatecake.entities.myList.ListMovieEntity
//import com.chocolatecake.local.database.dto.myList.ListMovieLocalDto
//import com.chocolatecake.repository.mappers.Mapper
//import javax.inject.Inject
//
//class LocalListMovieMapper @Inject constructor() : Mapper<ListMovieEntity, ListMovieLocalDto> {
//    override fun map(input: ListMovieEntity): ListMovieLocalDto {
//        return ListMovieLocalDto(
//            mediaId = input.mediaId,
//            listId = input.listId,
//            mediaType = input.mediaType,
//            nameList = input.nameList,
//            posterPath = input.posterPath,
//        )
//    }
//}