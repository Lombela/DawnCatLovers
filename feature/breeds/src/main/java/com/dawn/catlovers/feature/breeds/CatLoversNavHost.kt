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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CatLoversNavHost() {
    val navController = rememberNavController()
    val openFavorites = {
        navController.navigate(BreedRoutes.Favorites) {
            popUpTo(BreedRoutes.Browse) { inclusive = false }
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
                    onOpenFilters = { navController.navigate(BreedRoutes.Filters) },
                    onOpenFavorites = openFavorites,
                )
            }
            composable(BreedRoutes.Favorites) {
                FavoritesRoute(
                    onOpenBrowse = {
                        navController.navigate(BreedRoutes.Browse) {
                            popUpTo(BreedRoutes.Browse) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    onOpenBreed = { navController.navigate(BreedRoutes.details(it)) },
                    onOpenFilters = { navController.navigate(BreedRoutes.Filters) },
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
                )
            }
        }
    }
}
