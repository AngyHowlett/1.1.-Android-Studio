package ru.netology.a1_1_androidstudio.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.netology.a1_1_androidstudio.R
import ru.netology.a1_1_androidstudio.databinding.IntentHandlerActivityBinding

class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = IntentHandlerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent?.let {
            if (it.action != Intent.ACTION_SEND)
                return@let

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                Snackbar.make(
                    binding.root,
                    R.string.error_empty_content,
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(android.R.string.ok) { finish() }
                    .show()
                return@let
            }

        }

    }
}