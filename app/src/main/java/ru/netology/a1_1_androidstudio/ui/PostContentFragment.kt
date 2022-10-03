package ru.netology.a1_1_androidstudio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.a1_1_androidstudio.databinding.PostContentFragmentBinding


class PostContentFragment : Fragment() {

    private val args by navArgs<PostContentFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return PostContentFragmentBinding.inflate(layoutInflater, container, false)
            .also { binding ->
                binding.editText.setText(args.initialContent)
                binding.editText.requestFocus()

                binding.ok.setOnClickListener {
                    if (!binding.editText.text.isNullOrBlank()) {
                        val resultBundle = Bundle(1)
                        resultBundle.putString(RESULT_KEY, binding.editText.text.toString())
                        findNavController().previousBackStackEntry
                        if (args.caller == FeedFragment.CR_FEED) {
                            setFragmentResult(REQUEST_KEY_FEED, resultBundle)
                        } else if (args.caller == SinglePostFragment.CR_POST){
                            setFragmentResult(REQUEST_KEY_POST, resultBundle)
                        }
                    }
                    findNavController().popBackStack()
                }
            }.root
    }


    companion object {
        const val RESULT_KEY = "postNewContent"
        const val REQUEST_KEY_FEED = "requestKeyFeed"
        const val REQUEST_KEY_POST = "requestKeyPost"
    }

}