package com.example.lab1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextQuestion = findViewById<EditText>(R.id.editTextQuestion)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonSubmit.setOnClickListener {
            val question = editTextQuestion.text.toString().trim()
            val selectedOptionId = radioGroup.checkedRadioButtonId
            if (question.isEmpty() || selectedOptionId == -1) {
                Toast.makeText(this, "Будь ласка, введіть питання і виберіть відповідь", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
            val answer = selectedRadioButton.text.toString()

            textViewResult.text = "Question: $question\nAnswer: $answer"
        }
    }
}