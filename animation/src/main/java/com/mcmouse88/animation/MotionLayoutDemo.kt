package com.mcmouse88.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene

/**
 * 1. Motion Scene
 *     a. start
 *     b. end
 *     c. transition
 * 2. Progress
 */

@OptIn(ExperimentalMotionApi::class)
@Composable
@Preview
fun MotionLayoutDemo() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var progress by remember { mutableFloatStateOf(0f) }
        Slider(value = progress, onValueChange = { progress = it })
        
        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        val scene = remember {
            context
                .resources
                .openRawResource(R.raw.motion_scene)
                .readBytes()
                .decodeToString()

        }

        MotionLayout(
            motionScene = MotionScene(content = scene),
            progress = progress,
            modifier = Modifier.fillMaxSize()
        ) {
            val props = motionProperties(id = "my_box")
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = props.value.int("radius").dp))
                    .background(Color.DarkGray)
                    .layoutId("my_box")
            ) {

            }
        }
    }
}