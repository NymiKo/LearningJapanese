package com.example.peil.ui.screens.bottom_nav_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.peil.R
import com.example.peil.ui.navigation.BottomBarScreen
import com.example.peil.ui.navigation.BottomNavGraph
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue
import com.example.peil.util.sharedPreferencesUser

@Composable
fun NavigationBarWithContent(
    modifier: Modifier = Modifier,
    onWelcomeScreen: () -> Unit
) {
    if (sharedPreferencesUser(LocalContext.current).getString("token", null).isNullOrEmpty()) {
        onWelcomeScreen()
    }

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBottomBar(navController = navController)
        },
        content = { innerPadding ->
            BottomNavGraph(modifier = Modifier.padding(innerPadding), navController = navController)
        }
    )
}

@Composable
private fun NavigationBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Study,
        BottomBarScreen.Profile
    )

    screens.forEach {  screen ->

    }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                label = { Text(text = screen.title) },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
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
}
