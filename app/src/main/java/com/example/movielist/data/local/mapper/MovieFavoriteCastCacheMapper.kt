package com.example.movielist.data.local.mapper

import com.example.movielist.data.local.entity.MovieFavoriteCastCacheEntity
import com.example.movielist.domain.entity.MovieCast
import com.example.movielist.util.EntityMapper
import javax.inject.Inject

class MovieFavoriteCastCacheMapper @Inject constructor(

) : EntityMapper<MovieFavoriteCastCacheEntity, MovieCast> {
    override fun mapFromEntity(entity: MovieFavoriteCastCacheEntity): MovieCast {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: MovieCast): MovieFavoriteCastCacheEntity {
        TODO("Not yet implemented")
    }

}