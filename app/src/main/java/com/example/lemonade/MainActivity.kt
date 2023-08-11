package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Lemonade() {
    LemonadeButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}



@Composable
fun LemonadeButtonAndImage(modifier: Modifier = Modifier) {
    var taps by remember { mutableStateOf( 0) }
    var tapsNeeded  by remember { mutableStateOf((2..4).random())} //taps_need is a random number between 2 and 4


    val reset = tapsNeeded + 1

    var imageResource = if(taps == 0)
    {
        R.drawable.lemon_tree
    }else
    {
        R.drawable.lemon_squeeze
    }

    var text = if(taps == 0)
    {
        R.string.tap
    }else
    {
        R.string.keep_tapping
    }

    if(taps == tapsNeeded) {
        imageResource = R.drawable.lemon_drink
        text = R.string.drink
    }
    else if(taps > tapsNeeded)
    {
        imageResource = R.drawable.lemon_restart
        text = R.string.startAgain
    }



    Button(onClick = {
        taps++
        if (taps > reset)
        {
            taps = 0
            tapsNeeded  = (2..4).random()
        }
    }, colors = ButtonDefaults.buttonColors( containerColor = Color.Transparent
    ))
    {
        Column (modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){

            Text(text = "Lemonade", textAlign = TextAlign.Center, color = Color.Black)
            Image(
                painter = painterResource(imageResource),
                contentDescription = taps.toString()
            )
            Text(text = stringResource(text), color = Color.Black)
            Text(text = "Taps: $taps", color = Color.Black)
            Text(text = "Taps Needed: ${tapsNeeded + 1}", color = Color.Black)
        }
    }



}