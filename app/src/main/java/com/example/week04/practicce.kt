package com.example.week04

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.w3c.dom.Text
import java.io.File
import java.util.Scanner

fun main(){
    prac6()

}
fun pracice(){
    val item5 = Array<String>(5){i->(i*i).toString()}
}

fun practice2(one:String, two:String) = "$one $two"

fun practice3(one:String="ad",two: String="bye") = "$one $two"

fun prac4(){
    val functionType1 : ()-> Unit // = {println("HelloWorld")}
    functionType1 = {println("HelloWorld")}
    val functionType2 : (Int, String) -> String
    functionType2 = {age, name ->
        "나이 : $age 이름 : $name"
    }
    functionType1()
    val rv = functionType2(12,"james")
    println(rv)
}

fun prac5(func:()->Unit):()->Unit{
    val func2 = {
        println("Higher function Type")
    }
    return func2
}

fun prac6(){
    val scan = Scanner(File("C:\\Users\\eotn2\\MobileProgramming\\app\\src\\main\\java\\com\\example\\week04\\client.txt"))
    while(scan.hasNextLine()){
        val data = scan.next()
        println(data)
    }
}
@Preview
@Composable
fun calulateBMI(){
    var checked by rememberSaveable{ mutableStateOf(false)}
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var label by rememberSaveable {
        mutableStateOf("키(cm)")
    }
    var textChange  = { text1 : String ->
        text = text1
    }
    Column {
        Row{
            Text(text = "키 입력 단위 미터(m)?")
            Switch(checked = checked, onCheckedChange = {
                checked = it
                if(checked)
                    label = "키(m)"
                else
                    label = "키(cm)"
            })
        }
        TextFieldWithM(text, textChange, label)
        TextField(
            value = text,
            onValueChange = {text= it},
            label = {Text("몸무게(kg)")})
        Text("BMI 체크")
    }
}

@Composable
fun TextFieldWithM(text:String, onTextChange :(String)->Unit, label : String ){
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = {Text(label)}
    )
}

data class City(val name : String, val country : String)

val CitySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country)},
        restore = { City(it[nameKey] as String, it[countryKey] as String)}
    )
}

val CitySaver2 = listSaver<City, Any>(
    save = { listOf(it.name, it.country)},
    restore = { City(it[0] as String, it[1] as String)}
)
@Preview
@Composable
fun CityScreen(){
    var selectedCity = rememberSaveable(stateSaver = CitySaver2) {
        mutableStateOf(City("madrid", "Spain"))
    }
    selectedCity.value = selectedCity.value.copy(name =  "asd")
    Column {
        Text(selectedCity.value.name)
    }
}