package com.example.geoquize

import android.app.Activity
import android.content.Intent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.TextView
import com.bignerdranch.android.geoquiz.QuizViewModel

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE="com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : ComponentActivity() {
    private lateinit var answerTextView:TextView
    private lateinit var showAnswerButton: Button
    private var answerIsTrue=false
    //private var cheatCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
       // cheatCount = savedInstanceState?.getInt("CHEAT_COUNT", 0) ?: 0
        val apiVersionTextView = findViewById<TextView>(R.id.api_version_text_view)
        apiVersionTextView.text = "API Level: ${Build.VERSION.SDK_INT}"
        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)
        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)

        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)
        answerTextView=findViewById(R.id.answer_text_view)
        showAnswerButton=findViewById(R.id.show_answer_button)

        showAnswerButton.setOnClickListener(){
            //if (cheatCount < 3){
            val answerText=when{
                answerIsTrue->R.string.true_button
                else-> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShowResult(true)
               // cheatCount++
        }
           /* if (cheatCount == 3) {
                showAnswerButton.isEnabled = false
           }
        }*/
    }
    private fun setAnswerShowResult (isAnswerShown:Boolean){
        val data=Intent().apply{
            putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown)
        }
        setResult(Activity.RESULT_OK,data)
    }


    companion object {
    fun newIntent(packageContext: Context,answerIsTrue:Boolean):Intent{
        return Intent(packageContext,CheatActivity::class.java).apply{
            putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue)
        }
    }
}
}