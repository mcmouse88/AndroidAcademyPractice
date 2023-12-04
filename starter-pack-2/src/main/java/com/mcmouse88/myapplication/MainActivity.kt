package com.mcmouse88.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mcmouse88.myapplication.ui.components.ErrorDescription
import com.mcmouse88.myapplication.ui.theme.AndroidAcademyPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAcademyPracticeTheme {
                ErrorDescription(title = "Whoops, we couldn't load the data!", errorCode = "AA-31", onReportClick = {  })
            }
        }
    }
}