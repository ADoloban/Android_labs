package com.example.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    private lateinit var editText: EditText
    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        editText = view.findViewById(R.id.editTextQuestion)
        radioGroup = view.findViewById(R.id.radioGroup)
        val buttonOk = view.findViewById<Button>(R.id.buttonOk)

        buttonOk.setOnClickListener {
            val question = editText.text.toString().trim()
            val selectedOptionId = radioGroup.checkedRadioButtonId

            if (question.isEmpty() || selectedOptionId == -1) {
                Toast.makeText(activity, "Будь ласка, введіть питання і виберіть відповідь", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButton = view.findViewById<RadioButton>(selectedOptionId)
            val answer = selectedRadioButton.text.toString()

            val resultFragment = ResultFragment.newInstance(question, answer)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, resultFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    fun clearForm() {
        editText.text.clear()
        radioGroup.clearCheck()
    }
}