package com.example.pickanumberfrontend

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import kotlin.random.Random

// PUBLIC_INTERFACE
class MainActivity : AppCompatActivity() {
    // Game state
    private var correctNumber: Int = 0
    private var score: Int = 0
    private var gameActive: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Views
        val scoreText: TextView = findViewById(R.id.scoreText)
        val guessInput: EditText = findViewById(R.id.guessInput)
        val submitBtn: Button = findViewById(R.id.submitBtn)
        val resultText: TextView = findViewById(R.id.resultText)
        val restartBtn: Button = findViewById(R.id.restartBtn)

        // Generates a new random number [1, 10]
        fun generateRandomNumber() {
            correctNumber = Random.nextInt(1, 11)
            gameActive = true
            resultText.text = ""
            guessInput.setText("")
        }

        // Initialize
        generateRandomNumber()
        updateScore(scoreText)

        // Handle guess submission
        submitBtn.setOnClickListener {
            if (!gameActive) return@setOnClickListener

            val guessStr = guessInput.text.toString()
            val guess = guessStr.toIntOrNull()

            if (guess == null || guess < 1 || guess > 10) {
                resultText.text = getString(R.string.invalid_input)
                resultText.setTextColor(getColor(R.color.secondary))
                return@setOnClickListener
            }

            if (guess == correctNumber) {
                resultText.text = getString(R.string.win)
                resultText.setTextColor(getColor(R.color.primary))
                score += 1
                updateScore(scoreText)
                gameActive = false
            } else {
                resultText.text = getString(R.string.lose)
                resultText.setTextColor(getColor(R.color.secondary))
                gameActive = false
            }
        }

        // Handle restart button
        restartBtn.setOnClickListener {
            generateRandomNumber()
            resultText.text = ""
            guessInput.setText("")
            gameActive = true
        }
    }

    // PUBLIC_INTERFACE
    private fun updateScore(scoreText: TextView) {
        /**
         * Update the score display with the current score.
         */
        scoreText.text = "Score: $score"
    }

}
