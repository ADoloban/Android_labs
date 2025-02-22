package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var editText: EditText
    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        dbHelper = DatabaseHelper(requireContext())

        editText = view.findViewById(R.id.editTextQuestion)
        radioGroup = view.findViewById(R.id.radioGroup)
        val buttonOk = view.findViewById<Button>(R.id.buttonOk)
        val buttonOpen = view.findViewById<Button>(R.id.buttonOpen)

        buttonOk.setOnClickListener {
            val question = editText.text.toString().trim()
            val selectedOptionId = radioGroup.checkedRadioButtonId

            if (question.isEmpty() || selectedOptionId == -1) {
                Toast.makeText(activity, "Будь ласка, введіть питання і виберіть відповідь", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButton = view.findViewById<RadioButton>(selectedOptionId)
            val answer = selectedRadioButton.text.toString()

            val success = dbHelper.insertData(question, answer)
            if (success) {
                Toast.makeText(activity, "Дані збережено!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Помилка запису!", Toast.LENGTH_SHORT).show()
            }

            val resultFragment = ResultFragment.newInstance(question, answer)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, resultFragment)
                .addToBackStack(null)
                .commit()
        }

        buttonOpen.setOnClickListener {
            val intent = Intent(activity, ViewDataActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun clearForm() {
        editText.text.clear()
        radioGroup.clearCheck()
    }
}