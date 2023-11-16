package mk.ukim.finki.lab_intents

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.lab_intents.viewmodels.ResultViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var resultView: TextView
    private lateinit var explicitButton: Button
    private lateinit var implicitButton: Button
    private lateinit var imageButton: Button
    private lateinit var resultViewModel: ResultViewModel
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val resultString = data?.getStringExtra("result")

                if (resultString != null) {
                    resultViewModel.setResultValue(resultString)
                }

                if (data?.data != null) {
                    Intent(Intent.ACTION_VIEW).apply {
                        data.data?.let { i ->
                            setDataAndType(i, "image/*")
                        }
                    }.let { i ->
                        startActivity(i)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.textView)
        explicitButton = findViewById(R.id.buttonExplicit)
        implicitButton = findViewById(R.id.buttonImplicit)
        imageButton = findViewById(R.id.buttonImage)

        resultViewModel = ViewModelProvider(this)[ResultViewModel::class.java]

        resultViewModel.getResult().observe(this) {
            resultView.text = resultViewModel.getResultValue()
        }

        explicitButton.setOnClickListener {
            Intent(this, ExplicitActivity::class.java).let { i ->
                i.putExtra("result", resultViewModel.getResultValue())
                resultLauncher.launch(i)
            }
        }

        implicitButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                action = "mk.ukim.finki.lab_intents.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { i ->
                startActivity(i)
            }
        }

        imageButton.setOnClickListener {
            Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }.let { i ->
                resultLauncher.launch(i)
            }
        }
    }
}