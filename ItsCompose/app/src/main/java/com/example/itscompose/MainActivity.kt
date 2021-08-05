package com.example.itscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itscompose.ui.theme.ItsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItsComposeTheme() {
                PeaceApp()
            }
        }
    }
}

@Composable
fun PeaceApp() {
    Scaffold(
        content = {
            ProfileContent()
        }
    )
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(text = "Hello $name!")
        Text(text = "Hello Pyeonghwa")

        Image(painter = painterResource(id = R.drawable.dog), contentDescription = "So cute")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ItsComposeTheme {
        Greeting("Android")
    }
}