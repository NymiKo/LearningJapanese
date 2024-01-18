package com.example.peil

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.peil.ui.navigation.NavGraph
import com.example.peil.ui.screens.lessons_list.LessonsListScreen
import com.example.peil.ui.screens.lessons_list.LessonsListViewModel
import com.example.peil.ui.screens.profile.ProfileScreen
import com.example.peil.ui.theme.PeilTheme
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue
import com.example.peil.util.sharedPreferencesUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoading = mutableStateOf(true)

//        lifecycleScope.launch {
//            isLoading.value = false
//        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isLoading.value
            }
        }

        setContent {
            PeilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                    isLoading.value = false
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarWithContent(
    modifier: Modifier = Modifier,
    onWelcomeScreen: () -> Unit,
    onLearningLesson: (idLesson: Int) -> Unit,
    lessonsListViewModel: LessonsListViewModel
) {
    if (sharedPreferencesUser(LocalContext.current).getString("token", null).isNullOrEmpty()) {
        onWelcomeScreen()
    }

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        "Учиться" to R.drawable.ic_home,
        "Повторение" to R.drawable.ic_repeat,
        "Профиль" to R.drawable.ic_person
    )
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        label = { Text(text = item.first) },
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = null
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = baseBlue,
                            selectedTextColor = baseBlue,
                            selectedIconColor = White
                        )
                    )
                }
            }
        },
        content = { innerPadding ->
            NavigationItemsContent(
                modifier = Modifier.padding(innerPadding),
                state = selectedItem,
                lessonsListViewModel = lessonsListViewModel,
                onLearningLesson = onLearningLesson
            )
        }
    )
}

@Composable
fun NavigationItemsContent(
    modifier: Modifier = Modifier,
    state: Int,
    lessonsListViewModel: LessonsListViewModel,
    onLearningLesson: (idLesson: Int) -> Unit
) {
    when (state) {
        0 -> {
            LessonsListScreen(modifier, onLearningLesson::invoke, viewModel = lessonsListViewModel)
        }

        1 -> Text(text = "Вкладка 2", modifier)
        2 -> ProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeilTheme {
        NavigationBarWithContent(
            onWelcomeScreen = { },
            onLearningLesson = {},
            lessonsListViewModel = hiltViewModel()
        )
    }
}