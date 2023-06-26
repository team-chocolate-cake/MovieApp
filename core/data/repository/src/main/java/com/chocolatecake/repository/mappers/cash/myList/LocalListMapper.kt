//package com.chocolatecake.repository.mappers.cash.myList
//
//import com.chocolatecake.local.database.dto.movie.MovieLocalDto
//import com.chocolatecake.local.database.dto.myList.ListLocalDto
//import com.chocolatecake.remote.response.dto.ListRemoteDto
//import com.chocolatecake.remote.response.dto.MovieRemoteDto
//import com.chocolatecake.repository.BuildConfig
//import com.chocolatecake.repository.mappers.Mapper
//import javax.inject.Inject
//
//class LocalListMapper @Inject constructor() : Mapper<ListRemoteDto, ListLocalDto> {
//    override fun map(input: ListRemoteDto): ListLocalDto {
//        return ListLocalDto(
//            itemCount = input.itemCount ?: 0,
//            listType = input.listType ?: "",
//            name = input.name ?: "",
//            id = 0
//        )
//    }
//}