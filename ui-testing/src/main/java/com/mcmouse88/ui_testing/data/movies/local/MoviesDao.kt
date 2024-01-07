package com.mcmouse88.ui_testing.data.movies.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction

@Dao
interface MoviesDao {

    @[Transaction Insert(onConflict = OnConflictStrategy.IGNORE)]
    suspend fun insertAll(searchMovies: List<MovieEntity>)
}