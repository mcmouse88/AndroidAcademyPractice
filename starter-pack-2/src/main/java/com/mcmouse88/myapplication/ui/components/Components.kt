package com.mcmouse88.myapplication.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mcmouse88.myapplication.R
import com.mcmouse88.myapplication.ui.theme.AndroidAcademyPracticeTheme

@Composable
fun ErrorImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.err_image),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(percent = 25))
            .border(
                border = BorderStroke(
                    width = 8.dp,
                    color = MaterialTheme.colors.primary
                ),
                shape = RoundedCornerShape(percent = 25)
            ),
    )
    RoundedCornerShape(percent = 25)
}

@Composable
fun HeadingText(
    text: String, // "Whoops, we couldn't load the data!"
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun TextWithBoldSuffix(
    prefix: String, // "Error code: "
    suffix: String, // "AA-31"
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append(prefix)
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(suffix)
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ContactSupportText(
    onContactClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.seeing_this_message_too_often))
        append(stringResource(R.string.contact))
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = Color.Blue
            )
        ) {
            withAnnotation(tag = "help_tag", annotation = "www.google.com") {
                append("tech support")
            }
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations("help_tag", offset, offset)
                .firstOrNull()
                ?.let { annotation ->
                    annotation.item // www.google.com
                    onContactClicked.invoke()
                }
        },
        style = TextStyle.Default.merge(
            TextStyle(textAlign = TextAlign.Center)
        ),
        modifier = modifier
    )
}

@Composable
fun ButtonComponent(
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int? = null
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(percent = 50),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
        modifier = modifier
    ) {
        Text(text = buttonLabel)
        iconId?.let {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun ErrorDescription(
    title: String,
    errorCode: String,
    onReportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ErrorImage()
        HeadingText(
            text = title,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 32.dp)
        )
        TextWithBoldSuffix(
            prefix = "Error code: ",
            suffix = errorCode,
            modifier = Modifier.padding(top = 16.dp)
        )
        ContactSupportText(
            onContactClicked = onReportClick,
            modifier = Modifier.padding(top = 8.dp)
        )
        ButtonComponent(
            buttonLabel = stringResource(id = R.string.refresh),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            iconId = R.drawable.ic_refresh
        )
    }
}

@Preview(
    group = "Error Image",
    showBackground = true
)
@Composable
fun ErrorImagePreview() {
    AndroidAcademyPracticeTheme {
        ErrorImage()
    }
}

@Preview(
    group = "Button Component"
)
@Composable
fun ButtonComponentPreview() {
    AndroidAcademyPracticeTheme {
        ButtonComponent(
            buttonLabel = stringResource(id = R.string.refresh),
            onClick = {},
            iconId = R.drawable.ic_refresh
        )
    }
}

@Preview(
    group = "Error Description",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ErrorDescriptionPreview() {
    AndroidAcademyPracticeTheme {
        ErrorDescription(
            title = "Whoops, we couldn't load the data!",
            errorCode = "AA-31",
            onReportClick = {}
        )
    }
}