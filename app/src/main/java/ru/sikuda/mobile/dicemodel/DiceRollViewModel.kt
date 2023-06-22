package ru.sikuda.mobile.dicemodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.Serializable
import kotlin.random.Random

data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
): Serializable

class DiceRollViewModel(
    val saveStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val strKeyStote = "dices"
    }


    //val uiState = saveStateHandle.getStateFlow(strKeyStote, DiceUiState())
    // Expose screen UI state
    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

//    var uiData = mutableStateOf(
//        saveStateHandle.get<DiceUiState>(strKeyStote) ?: DiceUiState()
//    )
//        private set


    // Handle business logic
    fun rollDice() {
        _uiState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(from = 1, until = 7),
                secondDieValue = Random.nextInt(from = 1, until = 7),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
//        val newValue = DiceUiState(
//            firstDieValue = Random.nextInt(from = 1, until = 7),
//            secondDieValue = Random.nextInt(from = 1, until = 7),
//            //numberOfRolls = uiData.value.numberOfRolls + 1,
//            numberOfRolls = uiState.value.numberOfRolls + 1,
//        )
//        //uiData.value = newValue
//        saveStateHandle.set(strKeyStote, newValue)

    }
}
