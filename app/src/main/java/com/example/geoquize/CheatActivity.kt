package com.example.geoquize

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.TextView

class CheatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)

        val resultTextView = findViewById<TextView>(R.id.result_text_view)
        resultTextView.text = "Correct Answers: $correctAnswers"

        val repeatButton = findViewById<Button>(R.id.repeat_button)
        repeatButton.setOnClickListener {
            // Создан интент для перехода обратно к MainActivity
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)*/
            finish()
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