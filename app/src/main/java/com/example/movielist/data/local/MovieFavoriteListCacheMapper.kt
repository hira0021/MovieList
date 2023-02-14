package com.example.movielist.data.local

import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.util.EntityMapper
import javax.inject.Inject

class MovieFavoriteListCacheMapper @Inject constructor(

): EntityMapper<MovieFavoriteListCacheEntity, MovieDiscoverResult> {

    override fun mapFromEntity(entity: MovieFavoriteListCacheEntity): MovieDiscoverResult {
        return MovieDiscoverResult(
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            genreIds = entity.genreIds,
            id = entity.id,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
    }

    override fun mapToEntity(domainModel: MovieDiscoverResult): MovieFavoriteListCacheEntity {
        return MovieFavoriteListCacheEntity(
            adult = domainModel.adult,
            backdropPath = domainModel.backdropPath,
            genreIds = domainModel.genreIds,
            id = domainModel.id,
            originalLanguage = domainModel.originalLanguage,
            originalTitle = domainModel.originalTitle,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            title = domainModel.title,
            video = domainModel.video,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount
        )
    }

    fun mapFromEntityList(entities: List<MovieFavoriteListCacheEntity>): List<MovieDiscoverResult> {
        return entities.map { mapFromEntity(it) }
    }
}