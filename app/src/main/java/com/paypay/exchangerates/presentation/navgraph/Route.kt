package com.paypay.exchangerates.presentation.navgraph
import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
}