package com.chocolatecake.repository.mappers.remote

import com.chocolatecake.entities.myList.WatchlistRequestEntity
import com.chocolatecake.remote.request.WatchlistRequest
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class WatchlistRequestMapper @Inject constructor()
    :Mapper<WatchlistRequestEntity, WatchlistRequest> {
    override fun map(input: WatchlistRequestEntity): WatchlistRequest {
        return WatchlistRequest(
            watchlist = input.watchlist,
            mediaId = input.mediaId,
            mediaType = input.mediaType,
        )
    }
}