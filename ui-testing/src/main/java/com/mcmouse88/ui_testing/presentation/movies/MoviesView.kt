package com.mcmouse88.ui_testing.presentation.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mcmouse88.ui_testing.R
import com.mcmouse88.ui_testing.domain.movies.SearchMovieWithMyReview

@Composable
internal fun MoviesView(
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state by viewModel.searchResult.collectAsStateWithLifecycle()

    MoviesView(
        queryInput = state.query,
        movies = state.movies,
        isSearchInProgress = state.isMovieLoading,
        searchResultPlaceholder = state.resultPlaceholder,
        onNewQuery = viewModel::onNewQuery
    )
}

@Composable
private fun MoviesView(
    queryInput: String,
    movies: List<SearchMovieWithMyReview>,
    searchResultPlaceholder: Int?,
    isSearchInProgress: Boolean,
    onNewQuery: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchView(
            input = queryInput,
            hint = stringResource(id = R.string.hint_search_query),
            onInputChanged = onNewQuery,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            SearchInProgressView(isInProgress = isSearchInProgress)
        }

        if (searchResultPlaceholder != null) {
            Text(
                text = stringResource(id = searchResultPlaceholder),
                color = colorResource(id = R.color.text_color_primary),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .wrapContentHeight()
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = integerResource(id = R.integer.search_result_span_count)),
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies) { movie ->
                MovieView(
                    movie = movie,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun SearchView(
    input: String,
    hint: String,
    onInputChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    inputTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = 14.sp
    ),
    hintTextStyle: TextStyle = TextStyle(
        color = colorResource(id = R.color.text_color_secondary),
        fontSize = 14.sp
    ),
    trailingView: @Composable () -> Unit
) {
    BasicTextField(
        value = input,
        singleLine = true,
        textStyle = inputTextStyle,
        onValueChange = onInputChanged,
        modifier = modifier.defaultMinSize(minHeight = 48.dp)
    ) { innerTextField ->
        Surface(
            shape = RoundedCornerShape(size = 12.dp),
            tonalElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    if (input.isEmpty()) {
                        Text(text = hint, style = hintTextStyle)
                    }
                    innerTextField.invoke()
                }
                trailingView.invoke()
            }
        }
    }
}

@Composable
private fun SearchInProgressView(isInProgress: Boolean) {
    if (isInProgress) {
        CircularProgressIndicator(modifier = Modifier.size(24.dp))
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_search_grey_16),
            contentDescription = stringResource(id = R.string.search_icon)
        )
    }
}

@Composable
private fun MovieView(
    movie: SearchMovieWithMyReview,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_rad)))
    ) {
        AsyncImage(
            model = movie.movie.thumbnail,
            contentDescription = stringResource(id = R.string.content_cinema_poster),
            placeholder = painterResource(id = R.drawable.ic_cinema_grey_100),
            error = painterResource(id = R.drawable.ic_cinema_grey_100),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(ratio = 2f / 3f)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.poster_background))
        )

        Text(
            text = movie.movie.title.uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.color_titles),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x20000000),
                            Color(0x00000000)
                        )
                    )
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen.space_small),
                    vertical = dimensionResource(id = R.dimen.space_medium)
                )
        )
    }
}