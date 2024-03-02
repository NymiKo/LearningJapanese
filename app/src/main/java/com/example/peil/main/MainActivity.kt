package com.example.peil.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.peil.ui.navigation.BottomNavGraph
import com.example.peil.ui.navigation.loginNavGraph
import com.example.peil.ui.screens.bottom_nav_bar.NavigationBarWithContent
import com.example.peil.ui.theme.PeilTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.isLoading }

        setContent {
            PeilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationBarWithContent(
                        navController = navController,
//                        onWelcomeScreen = {
//                            navController.navigate(loginNavGraph) {
//                                popUpTo(loginNavGraph) {
//                                    inclusive = true
//                                }
//                            }
//                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeilTheme {}
}