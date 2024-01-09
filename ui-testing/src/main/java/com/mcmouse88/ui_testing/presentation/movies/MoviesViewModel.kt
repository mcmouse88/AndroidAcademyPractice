package com.mcmouse88.ui_testing.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcmouse88.ui_testing.R
import com.mcmouse88.ui_testing.domain.movies.SearchMovieWithMyReview
import com.mcmouse88.ui_testing.domain.repository.MoviesRepository
import com.mcmouse88.ui_testing.utils.EMPTY
import com.mcmouse88.ui_testing.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

private const val TEXT_ENTERED_DEBOUNCE_MILLIS = 500L

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val queryFlow = MutableStateFlow(String.EMPTY)

    private val _searchResult = MutableStateFlow(MoviesResult())
    val searchResult: StateFlow<MoviesResult> = _searchResult.asStateFlow()

    init {
        viewModelScope.launch {
            queryFlow
                .sample(TEXT_ENTERED_DEBOUNCE_MILLIS)
                .mapLatest(::handleQuery)
                .collect()
        }
    }

    fun onNewQuery(query: String) {
        _searchResult.update { value ->
            value.copy(query = query)
        }
        queryFlow.value = query
    }

    private suspend fun handleQuery(query: String) {
        emitShowOrHideProgress(query)

        if (query.isNotEmpty()) {
            handleSearchMovie(query)
        }
    }

    private fun emitShowOrHideProgress(query: String) {
        _searchResult.update { value ->
            if (query.isEmpty()) {
                value.copy(
                    isMovieLoading = false,
                    movies = emptyList(),
                    resultPlaceholder = R.string.movies_placeholder
                )
            } else {
                value.copy(isMovieLoading = true)
            }
        }
    }

    private suspend fun handleSearchMovie(query: String) {
        delay(1.seconds)

        when (val moviesResult = moviesRepository.searchMoviesWithReviews(query)) {
            is Result.Success -> emitSuccessState(moviesResult.data)
            is Result.Error -> emitErrorState()
        }
    }

    private fun emitSuccessState(movies: List<SearchMovieWithMyReview>) {
        _searchResult.update { value ->
            value.copy(
                isMovieLoading = false,
                movies = movies,
                resultPlaceholder = if (movies.isEmpty()) R.string.empty_result else null
            )
        }
    }

    private fun emitErrorState() {
        _searchResult.update { value ->
            value.copy(
                isMovieLoading = false,
                movies = emptyList(),
                resultPlaceholder = R.string.search_error
            )
        }
    }
}