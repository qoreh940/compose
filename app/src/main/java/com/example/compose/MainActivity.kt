package com.example.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import com.example.compose.core.navigation.NavigationScreen
import com.example.compose.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                Surface {
                    NavigationScreen()
                }
            }
        }

    }
}


