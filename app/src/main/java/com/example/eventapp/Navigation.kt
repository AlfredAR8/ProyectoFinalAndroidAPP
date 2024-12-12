// Navigation.kt
package com.example.eventapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.eventapp.ui.main.CreateEventScreen
import com.example.eventapp.ui.main.EventDetailScreen
import com.example.eventapp.ui.main.EventListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "eventList", modifier = modifier) {
        composable("eventList") {
            EventListScreen(navController = navController)
        }
        composable(
            "createEvent"
        ) {
            CreateEventScreen(navController = navController)
        }
        composable(
            "eventDetail/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: 0
            EventDetailScreen(navController = navController, eventId = eventId)
        }
    }
}