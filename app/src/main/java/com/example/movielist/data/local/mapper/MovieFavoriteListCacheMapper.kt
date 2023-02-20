package com.example.movielist.data.local.mapper

import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.EntityMapper
import javax.inject.Inject

class MovieFavoriteListCacheMapper @Inject constructor(

): EntityMapper<MovieFavoriteListCacheEntity, MovieDetail> {

    override fun mapFromEntity(entity: MovieFavoriteListCacheEntity): MovieDetail {
        return MovieDetail(
            adult = entity.adult,
            backdropPath = entity.backdropPath,
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

    override fun mapToEntity(domainModel: MovieDetail): MovieFavoriteListCacheEntity {
        val genreList: MutableList<String> = arrayListOf()
        for (i in domainModel.movieGenres) {
            genreList.add(i.name)
        }


        return MovieFavoriteListCacheEntity(
            adult = domainModel.adult,
            backdropPath = domainModel.backdropPath,
            genreList = genreList.joinToString(),
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

    /*un mapFromEntityList(entities: List<MovieFavoriteListCacheEntity>): List<MovieDiscoverResult> {
        return entities.map { mapFromEntity(it) }
    }*/
}