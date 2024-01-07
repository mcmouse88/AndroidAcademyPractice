package com.mcmouse88.ui_testing.data.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mcmouse88.ui_testing.data.movies.local.MovieEntity
import com.mcmouse88.ui_testing.data.movies.local.MoviesDao
import com.mcmouse88.ui_testing.data.review.local.MyReviewEntity
import com.mcmouse88.ui_testing.data.review.local.MyReviewsDao

@Database(
    entities = [
        MovieEntity::class,
        MyReviewEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MoviesDb : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun myReviewDao(): MyReviewsDao

    companion object {
        fun create(context: Context): MoviesDb {
            return Room.databaseBuilder(
                context,
                MoviesDb::class.java,
                "movies_database.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}