package com.example.week04.example6


sealed class Routes (val route: String) {
    object Home : Routes("Home")
    object Contacts : Routes("Contacts")
    object Favorites : Routes("Favorites")
    object Login : Routes("Login")
    object Register : Routes("Register")
    object Welcome : Routes("Welcome")
    object Main : Routes("Main")
}