package com.example.geoquize

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.geoquize.ui.theme.GeoquizeTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

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