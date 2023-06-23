package com.chocolatecake.repository.mappers.remote
import com.chocolatecake.entities.myList.FavoriteBodyRequestEntity
import com.chocolatecake.remote.request.FavoriteRequest
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class RemoteFavoriteBodyMapper @Inject constructor()
    :Mapper<FavoriteBodyRequestEntity, FavoriteRequest> {
    override fun map(input: FavoriteBodyRequestEntity): FavoriteRequest {
        return FavoriteRequest(
            favorite = input.favorite,
            mediaId = input.mediaId,
            mediaType = input.mediaType,
        )
    }
}