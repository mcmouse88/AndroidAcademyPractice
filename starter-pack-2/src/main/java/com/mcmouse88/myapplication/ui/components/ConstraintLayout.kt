package com.mcmouse88.myapplication.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mcmouse88.myapplication.R
import com.mcmouse88.myapplication.ui.theme.AndroidAcademyPracticeTheme

@Composable
fun CustomConstraintLayout(

) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val button = createRef()
        val (image, heading, code, contact) = createRefs()
        val content = createVerticalChain(image, heading, code, contact, chainStyle = ChainStyle.Packed)
        constrain(content) {
            top.linkTo(parent.top)
            bottom.linkTo(button.top)
        }

        ErrorImage(
            modifier = Modifier
                .constrainAs(image) {
                    centerHorizontallyTo(parent)
                }
        )
        HeadingText(
            text = "Whoops, we couldn't load the data!",
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 32.dp)
                .constrainAs(heading) {
                    centerHorizontallyTo(parent)
                }
        )
        TextWithBoldSuffix(
            prefix = "Error code: ",
            suffix = "AA-31",
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(code) {
                    centerHorizontallyTo(parent)
                }
        )
        ContactSupportText(
            onContactClicked = {},
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(contact) {
                    centerHorizontallyTo(parent)
                }
        )
        ButtonComponent(
            buttonLabel = stringResource(id = R.string.refresh),
            onClick = {},
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            iconId = R.drawable.ic_refresh
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ConstraintLayoutPreview() {
    AndroidAcademyPracticeTheme {
        CustomConstraintLayout()
    }
}

