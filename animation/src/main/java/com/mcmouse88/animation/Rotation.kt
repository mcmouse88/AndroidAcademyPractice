package com.mcmouse88.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@JvmInline
value class Rotation(
    val value: Float
) {
    companion object
}

@Composable
fun RotatedIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Star,
    rotation: Rotation = Rotation(0f)
) {
    Image(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier
            .size(50.dp)
            .rotate(rotation.value)
    )
}

@Composable
fun RotationExample() {
    val initial: Rotation = remember { Rotation(0f) }
    val target: Rotation = remember { Rotation(180f) }

    var isActive by remember { mutableStateOf(false) }

    val rotation: Rotation by animateRotateAsState(
        targetValue = if (isActive) target else initial,
        animationSpec = tween(1_000)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isActive = !isActive }) {
            Text(text = "Toggle")
        }
        RotatedIcon(
            rotation = rotation
        )
    }
}

@Composable
fun animateRotateAsState(
    targetValue: Rotation,
    animationSpec: AnimationSpec<Rotation> = spring(),
    visibilityThreshold: Rotation = Rotation(0.01f),
    label: String = "RotationAnimation",
    finishedListener: ((Rotation) -> Unit)? = null
): State<Rotation> {
    return animateValueAsState(
        targetValue = targetValue,
        typeConverter = Rotation.RotationConverter,
        animationSpec = animationSpec,
        visibilityThreshold = visibilityThreshold,
        label = label,
        finishedListener = finishedListener
    )
}

val Rotation.Companion.RotationConverter: TwoWayConverter<Rotation, AnimationVector1D>
    get() = RotateToVector

private val RotateToVector: TwoWayConverter<Rotation, AnimationVector1D> =
    TwoWayConverter(
        convertFromVector = { vector ->
            Rotation(vector.value)
        },
        convertToVector = { rotation ->
            AnimationVector1D(rotation.value)
        }
    )


@Preview
@Composable
fun RotationPreview() {
    RotationExample()
}