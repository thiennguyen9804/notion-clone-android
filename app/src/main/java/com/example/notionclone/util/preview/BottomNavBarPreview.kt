package com.example.notionclone.util.route.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.notionclone.util.route.Destination
import com.example.notionclone.screen.home_screen.HomeScreenRoot
import com.example.notionclone.screen.home_screen.HomeTopBar
import com.example.notionclone.screen.search_screen.SearchScreen
import com.example.notionclone.ui.theme.NotionCloneTheme

@PreviewLightDark
@Composable
fun BottomNavBarPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.HOME
    var selectedDestination by rememberSaveable {
        mutableIntStateOf(startDestination.ordinal)
    }
    NotionCloneTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route
                when(currentDestination) {
                    Destination.HOME.route -> HomeTopBar(
                        imageUrl = "",
                        name = "thien_nguyen_9804",
                    )
                    else -> {}
                }
            },
            bottomBar = {
                if(selectedDestination == Destination.AUTH.ordinal) {
                    Box {}
                } else {
                    NavigationBar(
                        windowInsets = NavigationBarDefaults.windowInsets,
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    ) {
                        Destination.entries.forEachIndexed { index, destination ->
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                                    indicatorColor = Color.Transparent,
                                ),

                                selected = selectedDestination == index,
                                onClick = {
                                    navController.navigate(route = destination.route)
                                    selectedDestination = index
                                },
                                icon = {
                                    Icon(
                                        if(selectedDestination == index)
                                            destination.selectedIcon!!
                                        else destination.unSelectedIcon!!,
                                        contentDescription = destination.contentDescription,
                                        modifier = Modifier.size(30.dp)
                                    )
                                },
                            )
                        }

                    }
                }
            }

        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination.route,
                modifier = Modifier.padding(innerPadding)
            ) {
//                Destination.entries.forEach { destination ->
//                    composable(destination.route) {
//                        when (destination) {
//                            Destination.HOME -> HomeScreenRoot()
//                            Destination.SEARCH -> SearchScreen()
//                        }
//                    }
//                }
            }
        }
    }

}