package com.mcmouse88.ui_testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mcmouse88.ui_testing.presentation.movies.MoviesView
import com.mcmouse88.ui_testing.ui.theme.AndroidAcademyPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAcademyPracticeTheme {
                MoviesView()
            }
        }
    }
}