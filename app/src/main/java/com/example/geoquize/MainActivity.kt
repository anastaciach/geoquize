package com.example.geoquize
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings.Global.putString
import android.util.Log
import android.view.View
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.geoquiz.QuizViewModel
import androidx.fragment.app.viewModels


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button
    private lateinit var nextButton:Button
    private lateinit var questionTextView: TextView
    private  val KEY_INDEX="index"
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        val currentIndex=savedInstanceState?.getInt(KEY_INDEX,0)?:0
        quizViewModel.currentIndex=currentIndex
        /*val provider:ViewModelProvider=ViewModelProvider(this)
        val quizViewModel=provider.get(QuizViewModel::class.java)
        Log.d(TAG,"Got a QuizViewModel:$quizViewModel")*/
        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)
        nextButton=findViewById(R.id.next_button)
        questionTextView=findViewById(R.id.question_text_view)

        trueButton.setOnClickListener{view: View->
              checkAnswer(true)

        }
        falseButton.setOnClickListener{view:View ->
                checkAnswer(false)

        }
        nextButton.setOnClickListener{view:View->
            quizViewModel.moveToNext()
           updateQuestion()
        }

        updateQuestion()

    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart() called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"OnResume() called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause() called")
    }
///???
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG,"onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop() called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }
    private fun updateQuestion(){
        val questionTextResId=quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        //Сделать кнопки видимыми
        trueButton.visibility = View.VISIBLE
        falseButton.visibility = View.VISIBLE
        trueButton.isClickable = true
        falseButton.isClickable = true
        if(quizViewModel.currentIndex == quizViewModel.questionBankSize - 1){
            nextButton.visibility=View.INVISIBLE
            nextButton.isClickable=false
        }
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer=quizViewModel.currentQuestionAnswer
        val messageResId=if (userAnswer==correctAnswer){
            R.string.correct_toast
            //quizViewModel.correctAnswers++
        }
            else { R.string.incorrect_toast
            }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
            .show()

        trueButton.visibility = View.INVISIBLE
        falseButton.visibility = View.INVISIBLE
        trueButton.isClickable = false
        falseButton.isClickable = false
        }

    }
