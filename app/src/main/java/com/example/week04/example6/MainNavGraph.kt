package com.example.week04.example6

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.week04.example6.screens.Contacts
import com.example.week04.example6.screens.Favorites
import com.example.week04.example6.screens.Home



fun NavGraphBuilder.MainNavGraph(navController: NavHostController){
    navigation(startDestination = "Home", route="Main"){

        composable(route = Routes.Home.route) {
            Home()
        }

        composable(
            route = Routes.Favorites.route
        ) {
            Favorites()
        }

        composable(route = Routes.Contacts.route) { it ->
            Contacts()
        }
    }
}