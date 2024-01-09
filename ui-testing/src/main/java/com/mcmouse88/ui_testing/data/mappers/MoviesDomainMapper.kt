package com.mcmouse88.ui_testing.data.mappers

import com.mcmouse88.ui_testing.data.movies.local.MovieEntity
import com.mcmouse88.ui_testing.domain.movies.SearchMovie

internal fun MovieEntity.toSearchMovie(): SearchMovie {
    return SearchMovie(
        id = this.id,
        title = this.title,
        thumbnail = this.thumbnail
    )
}