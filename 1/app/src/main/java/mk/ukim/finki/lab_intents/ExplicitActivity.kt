package mk.ukim.finki.lab_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.lab_intents.viewmodels.ResultViewModel

class ExplicitActivity : AppCompatActivity() {
    private lateinit var resultText: EditText
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button
    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        resultText = findViewById(R.id.resultText)
        submitButton = findViewById(R.id.submitButton)
        cancelButton = findViewById(R.id.cancelButton)

        resultViewModel = ViewModelProvider(this)[ResultViewModel::class.java]

        val bundle: Bundle? = intent.extras

        resultViewModel.getResult().observe(this) {
            resultText.setText(resultViewModel.getResultValue())
        }

        bundle?.getString("result")?.let { i -> resultViewModel.setResultValue(i) }

        submitButton.setOnClickListener {
            Intent().let { i ->
                i.putExtra("result", resultText.text.toString())
                setResult(RESULT_OK, i)
                finish()
            }
        }

        cancelButton.setOnClickListener {
            Intent().let { intent ->
                setResult(RESULT_CANCELED, intent)
                finish()
            }
        }
    }
}