package com.example.week04

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.week04.ui.theme.Week04Theme


val LocalColor = staticCompositionLocalOf { Color(0xFFffdbcf) }

@Composable
fun Composable1() {

    var color = if(isSystemInDarkTheme()){
        Color(0xFFa01d57)
    }else{
        Color(0xF2f24b2f)
    }
    Column {
        Composable2()
        CompositionLocalProvider (LocalColor provides color) {
            Composable3()
        }
    }
}

@Composable
fun Composable3() {
    Text("Compose 3", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider (LocalColor provides Color.Red) {
        Composable5()
    }
}

@Composable
fun Composable5() {
    Text("Compose 5", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider (LocalColor provides Color.Green) {
        Composable7()
    }
    CompositionLocalProvider (LocalColor provides Color.Yellow) {
        Composable8()
    }
}

@Composable
fun Composable8() {
    Text("Compose 8", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable7() {
    Text("Compose 7", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable2() {
    Composable4()
}

@Composable
fun Composable4() {
    Composable6()
}

@Composable
fun Composable6() {
    Text("Compose 6", modifier = Modifier.background(LocalColor.current))
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    Week04Theme {
        Composable1()
    }
}
