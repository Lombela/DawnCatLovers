package com.dawn.catlovers.feature.breeds

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

@Composable
fun CatLoversNavHost() {
    val navController = rememberNavController()
    val openFavorites = {
        navController.navigate(BreedRoutes.Favorites) {
            popUpTo(BreedRoutes.Browse) { inclusive = false }
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = BreedRoutes.Browse,
    ) {
        composable(BreedRoutes.Browse) {
            BrowseRoute(
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
