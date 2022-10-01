package ru.netology.a1_1_androidstudio.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.netology.a1_1_androidstudio.databinding.PostContentActivityBinding

class PostContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PostContentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            binding.editText.setText(extras.getString("content"))
        }

        binding.editText.requestFocus()
        binding.ok.setOnClickListener {
            val intent = Intent()
            val text = binding.editText.text
            if (text.isNullOrBlank()) {
                setResult(RESULT_CANCELED, intent)
            } else {
                val content = text.toString()
                intent.putExtra(RESULT_KEY, content)
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }

    object ResultContract : ActivityResultContract<String?, String?>() {
        override fun createIntent(context: Context, input: String?) : Intent {
            return Intent(
                context,
                PostContentActivity::class.java
            ).putExtra("content", input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if (resultCode == Activity.RESULT_OK) {
                intent?.getStringExtra(RESULT_KEY)
            } else null
        }


    private companion object {
        private const val RESULT_KEY = "postNewContent"
    }

}