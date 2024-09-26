package com.techbit.calculators

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techbit.calculators.ui.theme.CalculatorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            CalculatorsTheme {
                app()
            }
        }
    }
}

@Composable
fun app(modifier: Modifier=Modifier){
    var expressionList by remember {
        mutableStateOf(mutableStateListOf(""))
    }
    var selected by remember { mutableStateOf(0) }
    var expression by remember {
        mutableStateOf(expressionList[0])
    }
    expressionList[selected] = expression
    var result by remember {
        mutableStateOf("")
    }
    result = EvaluateExpression.evaluate(expression)

    Scaffold(
        topBar = {
            Row (Modifier.fillMaxWidth().padding(10.dp)){
                LazyRow (Modifier.weight(1f)){
                    items(expressionList.size){
                            i ->
                        if (i == selected){
                            Button(onClick = {
                                expression = expressionList[i]
                                selected = i
                            },Modifier.padding(10.dp)) {
                                Text(text ="tab "+(i+1).toString(), fontSize = 20.sp)
                            }
                        }
                        else{
                            OutlinedButton(onClick = {
                                expression = expressionList[i]
                                selected = i
                            },Modifier.padding(10.dp)) {
                                Text(text ="tab "+(i+1).toString(), fontSize = 20.sp)
                            }
                        }
                    }
                }
                Button(onClick = {
                    expressionList.add("")
                    selected = expressionList.size - 1
                    expression = expressionList[expressionList.size - 1]
                }, modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "+", fontSize = 30.sp)
                }
            }
        }

    ) {innerPadding->


            Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)

        )
            {


            Column {

                TextField(
                    value = expression,
                    onValueChange = { expression = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    textStyle = TextStyle(fontSize = 30.sp),
                    singleLine = true,
                    readOnly = true
                )
                Text(
                    text = result,
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                )
            }
            Column {
                Row() {
                    OutlinedButton(
                        onClick = { expression = "" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "AC", fontSize = 30.sp) }

                    OutlinedButton(
                        onClick = { expression += "(" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "(", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += ")" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = ")", fontSize = 30.sp) }

                    OutlinedButton(
                        onClick = { expression += "/" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "/", fontSize = 30.sp) }

                }
                // Number buttons
                Row() {
                    OutlinedButton(
                        onClick = { expression += "7" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "7", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "8" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "8", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "9" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "9", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "*" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "*", fontSize = 30.sp) }


                }
                Row() {
                    OutlinedButton(
                        onClick = { expression += "4" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "4", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "5" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "5", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "6" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "6", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "-" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "-", fontSize = 35.sp) }
                }
                Row() {
                    OutlinedButton(
                        onClick = { expression += "1" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "1", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "2" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "2", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "3" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "3", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "+" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "+", fontSize = 30.sp) }

                }
                Row() {
                    OutlinedButton(
                        onClick = { expression += "0" },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "0", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression += "." },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = ".", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression = expression.dropLast(1) },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "⌫", fontSize = 30.sp) }
                    OutlinedButton(
                        onClick = { expression = result },
                        Modifier.weight(1f).height(70.dp).padding(5.dp)
                    ) { Text(text = "=", fontSize = 30.sp) }
                }
            }

        }

    }
}