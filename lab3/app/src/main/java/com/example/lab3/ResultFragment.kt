package com.example.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    companion object {
        private const val ARG_QUESTION = "arg_question"
        private const val ARG_ANSWER = "arg_answer"

        fun newInstance(question: String, answer: String): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putString(ARG_QUESTION, question)
            args.putString(ARG_ANSWER, answer)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val textViewResult = view.findViewById<TextView>(R.id.textViewResult)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)

        val question = arguments?.getString(ARG_QUESTION)
        val answer = arguments?.getString(ARG_ANSWER)

        textViewResult.text = "Питання: $question\nВідповідь: $answer"

        buttonCancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()

            val inputFragment = requireActivity().supportFragmentManager.findFragmentByTag("InputFragment") as? InputFragment
            inputFragment?.clearForm()
        }

        return view
    }
}