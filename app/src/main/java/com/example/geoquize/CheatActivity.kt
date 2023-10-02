package com.example.geoquize

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.TextView

private const val EXTRA_ANSWER_IS_TRUE="com.bignerdranch.android.geoquiz.answer_is_true"
class CheatActivity : ComponentActivity() {
    private lateinit var answerTextView:TextView
    private lateinit var showAnswerButton: Button
    private var answerIsTrue=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)
        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)

        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)
        answerTextView=findViewById(R.id.answer_text_view)
        showAnswerButton=findViewById(R.id.show_answer_button)
        showAnswerButton.setOnclickListenner{
            valanswerText=when{
                answerIsTrue->R.string.true_button
                else-> R.string.false_button
            }
            answerTextView.setText(answerText)
        }
    }
companion object {
    fun newIntent(packageContext: Context,answerIsTrue:Boolean):Intent{
        return Intent(packageContext,CheatActivity::class.java).apply{
            putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue)
        }
    }
}
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoquizeTheme {
        Greeting("Android")
    }
}*/