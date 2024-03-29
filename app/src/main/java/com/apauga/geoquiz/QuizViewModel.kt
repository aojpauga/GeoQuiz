package com.apauga.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val Tag = "QuizViewModel"

class QuizViewModel : ViewModel(){

    private val questionBank = listOf(
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_oceans, true)
    )
    private val questionBankReadOnly = questionBank
    private val reversedQuestionBack = questionBankReadOnly.asReversed()

    var currentIndex = 0
    var isCheater = false

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveBack(){
        currentIndex = (currentIndex + 1) % reversedQuestionBack.size
    }
}