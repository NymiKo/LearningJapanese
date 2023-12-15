package com.example.peil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.peil.ui.screens.lessons_list.LessonsListScreen
import com.example.peil.ui.screens.welcome.WelcomeScreen
import com.example.peil.ui.theme.PeilTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                    WelcomeScreen()
                    //NavigationBarWithContent()
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
                    icon = { Icon(painter = painterResource(id = item.second), contentDescription = null) })
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