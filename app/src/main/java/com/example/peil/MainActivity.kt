package com.example.peil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.peil.ui.navigation.NavGraph
import com.example.peil.ui.screens.lessons_list.LessonsListScreen
import com.example.peil.ui.theme.PeilTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoading = mutableStateOf(true)

        lifecycleScope.launch {
            delay(1000)
            isLoading.value = false
        }

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
                }
            }
        }
    }
}

@Composable
fun NavigationBarWithContent(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        "Главная" to R.drawable.ic_home,
        "Повторение" to R.drawable.ic_repeat,
        "Профиль" to R.drawable.ic_person
    )

    Column {
        NavigationItemsContent(modifier = Modifier.weight(1f), state = selectedItem)
        NavigationBar {
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
                    })
            }
        }
    }
}

@Composable
fun NavigationItemsContent(modifier: Modifier = Modifier, state: Int) {
    when (state) {
        0 -> {
            LessonsListScreen(modifier)
        }

        1 -> Text(text = "Вкладка 2", modifier)
        2 -> Text(text = "Вкладка 3", modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeilTheme {
        NavigationBarWithContent()
    }
}