package com.paypay.exchangerates.presentation.mainActivity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paypay.exchangerates.domain.usecase.app_entry.ReadAppEntry
import com.paypay.exchangerates.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(readAppEntry: ReadAppEntry): ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value = Route.HomeScreen.route
            } else {
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(300) //We need to add this delay to show splash screen
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}