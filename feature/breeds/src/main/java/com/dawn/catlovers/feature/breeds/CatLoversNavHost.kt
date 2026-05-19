package com.dawn.catlovers.feature.breeds

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dawn.catlovers.feature.breeds.screen.BrowseRoute
import com.dawn.catlovers.feature.breeds.screen.DetailsRoute
import com.dawn.catlovers.feature.breeds.screen.FavoritesRoute
import com.dawn.catlovers.feature.breeds.screen.FiltersRoute
import com.dawn.catlovers.feature.breeds.screen.ProfileRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CatLoversNavHost() {
    val navController = rememberNavController()
    val openTopLevelDestination: (String) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(BreedRoutes.Browse) { inclusive = false }
            launchSingleTop = true
        }
    }
    val openBrowse = { openTopLevelDestination(BreedRoutes.Browse) }
    val openFavorites = { openTopLevelDestination(BreedRoutes.Favorites) }
    val openProfile = { openTopLevelDestination(BreedRoutes.Profile) }
    val openFilters = {
        navController.navigate(BreedRoutes.Filters) {
            launchSingleTop = true
        }
    }

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = BreedRoutes.Browse,
        ) {
            composable(BreedRoutes.Browse) {
                BrowseRoute(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onOpenBreed = { navController.navigate(BreedRoutes.details(it)) },
                    onOpenFilters = openFilters,
                    onOpenFavorites = openFavorites,
                    onOpenProfile = openProfile,
                )
            }
            composable(BreedRoutes.Favorites) {
                FavoritesRoute(
                    onOpenBrowse = openBrowse,
                    onOpenBreed = { navController.navigate(BreedRoutes.details(it)) },
                    onOpenFilters = openFilters,
                    onOpenProfile = openProfile,
                )
            }
            composable(BreedRoutes.Profile) {
                ProfileRoute(
                    onOpenBrowse = openBrowse,
                    onOpenFavorites = openFavorites,
                    onOpenFilters = openFilters,
                )
            }
            composable(
                route = BreedRoutes.Details,
                arguments = listOf(navArgument("breedId") { type = NavType.StringType }),
            ) {
                DetailsRoute(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onBack = { navController.popBackStack() },
                )
            }
            composable(BreedRoutes.Filters) {
                FiltersRoute(
                    onBack = { navController.popBackStack() },
                    onOpenBreed = { navController.navigate(BreedRoutes.details(it)) },
                    onOpenFavorites = openFavorites,
                    onOpenProfile = openProfile,
                )
            }
        }
    }
}
