package com.mcmouse88.androidacademypractice.presentation.review.rating

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.DEFAULT
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mcmouse88.androidacademypractice.R
import com.mcmouse88.androidacademypractice.presentation.review.common.RatingView
import com.mcmouse88.androidacademypractice.utils.DevicesPreview
import com.mcmouse88.androidacademypractice.utils.PhonePreview
import com.mcmouse88.androidacademypractice.utils.TabletPreview

@Composable
internal fun ReviewRatingView() {
    // val state by viewModel.state.collectAsStateWithLifecycle()

    // TODO: handle disabled state

}

@Composable
private fun ReviewRatingView(
    rating: Int,
    @StringRes error: Int?,
    onRatingChange: (rating: Int) -> Unit,
    onSubmit: () -> Unit,
    snackbarState: SnackbarHostState = remember { SnackbarHostState() }
) {
    if (error != null) {
        val message = stringResource(error)
        
        LaunchedEffect(snackbarState) {
            snackbarState.showSnackbar(
                message = message
            )
        }
    }

    Column {
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(horizontal = dimensionResource(id = R.dimen.space_normal))
        ) {
            Text(
                text = stringResource(id = R.string.review_rating_message),
                fontSize = 24.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            RatingView(
                rating = rating,
                onRatingChange = onRatingChange,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = dimensionResource(id = R.dimen.space_normal))
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            Button(
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.review_button_submit))
            }
        }
        SnackbarHost(hostState = snackbarState)
    }
}

/**
 * - name: set text which will be displayed above preview
 * - group: set group which may be choose on the pane of preview
 * - apiLevel: set api level which will be rendering on preview
 * - widthDp: set max width which will be rendering composable fun
 * - heightDp: set max height which will be rendering composable fun
 * - locale: set locale for settings language
 * - fontScale - simulate user's settings of font scale
 * - showSystemUi - on/off displayed StatusBar, ActionBar and System NavigationBar
 * - showBackground - on/off default or custom background on the window
 * - uiMode - bit mask which of 2 params: 1 - window (desktop, watch or phone) and color schema
 * - device - set a specific device for ui rendering
 *
 * widthDp and heightDp may be conflicted with parameter ***device***
 * [PhonePreview] is the custom annotation class for reusing [Preview]
 * [TabletPreview] is the another one, composable is supporting multi preview
 * [DevicesPreview] is the another one, which group by multi preview
 */
@Preview(
    name = "Simple preview",
    group = "Simple group",
    apiLevel = 33,
    widthDp = -1,
    heightDp = -1,
    locale = "en",
    fontScale = 1.5f,
    showSystemUi = true,
    showBackground = false,
    backgroundColor = 0,
    uiMode = UI_MODE_NIGHT_NO,
    device = DEFAULT
)
// @PhonePreview
// @TabletPreview
// @DevicesPreview
@Composable
fun ReviewRatingViewPreview() {
    ReviewRatingView(rating = 3, error = null, onRatingChange = {}, onSubmit = {})
}