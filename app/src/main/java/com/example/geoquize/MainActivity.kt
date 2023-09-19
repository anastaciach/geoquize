package com.example.geoquize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button
    private lateinit var nextButton:Button
    private lateinit var questionTextView: TextView
    private var canAnswer = true
    private var canGoToNext = false
    private var correctAnswers = 0


    private  val KEY_INDEX="index"
    private val questionBank=listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }
        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)
        nextButton=findViewById(R.id.next_button)
        questionTextView=findViewById(R.id.question_text_view)

        trueButton.setOnClickListener{view: View->
            if (canAnswer) {
                checkAnswer(true)
                canAnswer = false
                canGoToNext = true
                updateButtonState()
            }
        }
        falseButton.setOnClickListener{view:View ->
            if (canAnswer) {
                checkAnswer(false)
                canAnswer = false
                canGoToNext = true
                updateButtonState()
            }
        }
        nextButton.setOnClickListener{view:View->
            currentIndex=(currentIndex + 1) % questionBank.size
           updateQuestion()
        }
        updateQuestion()
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(KEY_INDEX, currentIndex)
    }
    private fun updateButtonState() {
        trueButton.isEnabled = canAnswer
        falseButton.isEnabled = canAnswer
        nextButton.visibility = if (canGoToNext) View.VISIBLE else View.INVISIBLE
    }

    private fun updateQuestion(){
        val questionTextResId=questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        //сброс флагов перед след. вопросом
        canAnswer = true
        canGoToNext = false
        updateButtonState()

        if (currentIndex == questionBank.size - 1) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("CORRECT_ANSWERS", correctAnswers)
            startActivity(intent)
        }
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer=questionBank[currentIndex].answer
        val messageResId= if (userAnswer==correctAnswer){
            R.string.correct_toast
            correctAnswers++
        }
            else { R.string.incorrect_toast
            }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
            .show()
        }
    }
