package mk.ukim.finki.lab_intents

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ImplicitActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        textView = findViewById(R.id.activityImplicitTextView)

        textView.text = getActivities().joinToString("\n") { it.activityInfo.name }
    }

    private fun getActivities(): List<ResolveInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)

        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        return packageManager.queryIntentActivities(intent, 0)
    }
}