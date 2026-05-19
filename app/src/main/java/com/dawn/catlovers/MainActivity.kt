package com.dawn.catlovers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dawn.catlovers.core.designsystem.CatLoversTheme
import com.dawn.catlovers.feature.breeds.CatLoversNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatLoversTheme {
                CatLoversNavHost()
            }
        }
    }
}
