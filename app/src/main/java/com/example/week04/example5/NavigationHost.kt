package com.example.week04.example5

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week04.example5.NavRoutes
import com.example.week04.example5.screens.Contacts
import com.example.week04.example5.screens.Favorites
import com.example.week04.example5.screens.Home

@Composable
fun NagivationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ){
        composable(NavRoutes.Home.route){
            Home()
        }
        composable(NavRoutes.Contacts.route){
            Contacts()
        }
        composable(NavRoutes.Favorites.route){
            Favorites()
        }
    }
}