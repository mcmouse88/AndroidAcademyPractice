package com.mcmouse88.ui_testing.domain.movies

enum class Rating(val starsCount: Int) {
    BAD(starsCount = 1),
    LOW(starsCount = 2),
    MEDIUM(starsCount = 3),
    HIGH(starsCount = 4),
    BEST(starsCount = 5);

    companion object {
        fun mapToRating(rating: Int): Rating? = entries
            .find { it.starsCount == rating }
    }
}
