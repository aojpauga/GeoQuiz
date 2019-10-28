package com.apauga.geoquiz

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val Tag = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Tag, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener { View: View ->
            checkAnswer(true)
            trueButton.isEnabled = false
            trueButton.isClickable = false
            falseButton.isEnabled = false
            falseButton.isClickable = false
        }

        falseButton.setOnClickListener { View: View ->
            checkAnswer(false)
            falseButton.isEnabled = false
            falseButton.isClickable = false
            trueButton.isEnabled = false
            trueButton.isClickable = false
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
            falseButton.isEnabled = true
            falseButton.isClickable = true
            trueButton.isEnabled = true
            trueButton.isClickable = true
        }

        backButton.setOnClickListener { View:View->
            quizViewModel.moveBack()
            updateQuestion()
        }

        questionTextView.setOnClickListener { View: View ->
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(Tag,"onStart() called" )
    }

    override fun onResume() {
        super.onResume()
        Log.d(Tag, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Tag, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Tag, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Tag,"onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
