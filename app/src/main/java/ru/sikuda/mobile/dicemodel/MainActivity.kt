package ru.sikuda.mobile.dicemodel

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.sikuda.mobile.dicemodel.ui.theme.DiceModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    DiceRollScreen()
                }
            }
        }
    }
}


@Composable
fun DiceRollScreen(
    viewModel: DiceRollViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    //val value = viewModel.uiState.collectAsState(initial = "")

    val firstDieValue = if (uiState.firstDieValue == null) "" else uiState.firstDieValue.toString()
    val secondDieValue = if (uiState.secondDieValue == null) "" else uiState.secondDieValue.toString()

    // Update UI elements
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(firstDieValue)
        Spacer(modifier = Modifier.height(16.dp))
        Text(secondDieValue)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = uiState.numberOfRolls.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.rollDice() }) {
            Text("Roll")
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceModelTheme {
        Greeting("Android")
    }
}