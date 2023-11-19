package com.mcmouse88.androidacademypractice.presentation.review.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
internal fun RatingView(
    rating: Int,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    onRatingChange: (newRating: Int) -> Unit = {}
) {
    Row(modifier = modifier) {
        repeat(maxRating) {
            Icon(
                imageVector = if (it < rating) {
                    Icons.Filled.StarRate
                } else {
                    Icons.Filled.StarBorder
                },
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable { onRatingChange.invoke(it + 1) }
            )
        }
    }
}

@Preview
@Composable
fun RatingViewPreview(
    @PreviewParameter(RatingParams::class)
    rating: Int
) {
    RatingView(rating = rating)
}

class RatingParams : PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = (0..5).asSequence()
}