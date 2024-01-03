package com.mcmouse88.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene

@OptIn(ExperimentalMotionApi::class)
@Composable
@Preview
fun MotionLayoutYoutube() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var progress by remember { mutableFloatStateOf(0f) }

        Slider(
            value = progress,
            onValueChange = { progress = it },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        val content = remember {
            context
                .resources
                .openRawResource(R.raw.motion_scene_youtube)
                .readBytes()
                .decodeToString()
        }

        MotionLayout(
            motionScene = MotionScene(content = content),
            progress = progress,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .layoutId("video")
            )

            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .layoutId("right_content")
                    .padding(8.dp)
            ) {
                Text(
                    text = "Song name",
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .layoutId("bottom_content")
                    .padding(16.dp)
            ) {
                Text(text = "Song name", fontSize = 30.sp)
            }
        }
    }
}