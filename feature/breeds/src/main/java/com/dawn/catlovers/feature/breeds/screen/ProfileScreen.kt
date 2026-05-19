package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.component.BottomNavDestination
import com.dawn.catlovers.feature.breeds.component.BottomNavigationBar

@Composable
fun ProfileRoute(
    onOpenBrowse: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenFilters: () -> Unit,
) {
    ProfileScreen(
        onOpenBrowse = onOpenBrowse,
        onOpenFavorites = onOpenFavorites,
        onOpenFilters = onOpenFilters,
    )
}

@Composable
private fun ProfileScreen(
    onOpenBrowse: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenFilters: () -> Unit,
) {
    Scaffold(
        containerColor = CatLoversColors.Linen,
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = BottomNavDestination.Profile,
                onBrowseClick = onOpenBrowse,
                onFavoritesClick = onOpenFavorites,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            ProfileHeader(onOpenFilters = onOpenFilters)
            ProfileSignedOutContent()
        }
    }
}
