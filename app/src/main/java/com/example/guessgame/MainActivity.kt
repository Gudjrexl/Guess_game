package com.example.guessgame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.guessgame.databinding.NumberguessBinding
import com.example.guessgame.ui.theme.GuessGameTheme

class MainActivity : ComponentActivity() {
    private lateinit var bind : NumberguessBinding
    private val random = (0..100).random()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        bind = NumberguessBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        val range = bind.range
        val submit = bind.submit
        val result = bind.result
        var count = 0
        val rest = bind.reset

        rest.setOnClickListener {
            range.text.clear()
            result.text = "Attemp 0"
            count = 0
            submit.isEnabled = true}

        submit.setOnClickListener {
           result.text = "random number is $random and guessed no. is ${range.text.toString()}"
            if (range.text.toString() == ""){
                result.text = "Please enter a number"
            }
            else if (range.text.toString().toInt() == random){
                result.text = "You win "
            }
            else if (range.text.toString().toInt() - random in 1..9){
                result.text = "You are near, try within 10 smaller range, Attempt $count "
                count++
            }
            else if (random - range.text.toString().toInt() in 1..9 ){
                result.text = "You are near, try within 10 larger range, Attempt $count "
                count++
            }

            else if (range.text.toString().toInt() > random){
                result.text = "You guessed too high, Attempt $count"
                count++
            }
            else if (range.text.toString().toInt() < random){
                result.text = "You guessed too low, Attempt $count"
                count++
            }
            if (count == 10){
                result.text = "You lose"
                submit.isEnabled = false
            }




    }
}}