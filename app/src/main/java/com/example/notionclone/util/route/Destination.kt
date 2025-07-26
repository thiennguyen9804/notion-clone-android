package com.example.notionclone.util.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Adb
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector


enum class Destination(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector?,
    val unSelectedIcon: ImageVector?,
    val contentDescription: String?
) {
    HOME("homes", "Home", Icons.Default.Home, Icons.Outlined.Home,"Home"),
    SEARCH("search", "Search", Icons.Default.Search, Icons.Outlined.Search,"Search"),
    AUTH("auth", "Deep Link", null, null,"Deep Link")

}