package com.trippyheads.covires.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.trippyheads.covires.data.models.FeedbackModel
import com.trippyheads.covires.databinding.FragmentFeedbackBinding
import timber.log.Timber
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFeedbackBinding? = null
    private val binding get() = _binding!!

    private val firestore by lazy {
        Firebase.firestore
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)

        binding.etFBContact.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etFBContact.text.toString().isEmpty() || binding.etFBContact.text.toString().isBlank() || binding.etFBContact.text.toString().trim().length != 10) {
                    binding.etFBContact.error = "Please enter a valid phone number"
                }
            }
        })

        binding.etFBSuggestion.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etFBSuggestion.text.toString().isEmpty() || binding.etFBSuggestion.text.toString().isBlank()) {
                    binding.etFBSuggestion.error =
                        "Please don't provide the empty input for suggestions section"
                }
            }
        })

        binding.etFBDifficultyFaced.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etFBDifficultyFaced.text.toString().isEmpty() || binding.etFBDifficultyFaced.text.toString().isBlank()) {
                    binding.etFBDifficultyFaced.error =
                        "Please don't provide the empty input for diffculty faced section"
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFBSub.setOnClickListener {
            if (!checkWhetherFeedbackProvidedIsCorrect()) {
                return@setOnClickListener
            }

            val feedback = getFeedbackData()

            uploadFeedBack(feedback)
        }
    }

    private fun uploadFeedBack(feedback: FeedbackModel) {
        firestore
            .collection(FEEDBACK_COLLECTION)
            .document(feedback.userFeedbackId)
            .set(feedback)
            .addOnSuccessListener {
                Timber.d("Feedback Document successfully uploaded in firestore!")

                Snackbar.make(
                    binding.btnFBSub,
                    "Your feedback is successfully uploaded",
                    Snackbar.LENGTH_LONG
                ).show()

            }
            .addOnFailureListener { e ->
                Timber.w("Error updating feedback document ${e.printStackTrace()}")

                Snackbar.make(
                    binding.btnFBSub,
                    "Error Occured while uploading your feedback.Please try again",
                    Snackbar.LENGTH_LONG
                ).show()
            }
    }

    private fun checkWhetherFeedbackProvidedIsCorrect(): Boolean {
        var isCorrect = true

        if (binding.etFBContact.text.toString().isEmpty() || binding.etFBContact.text.toString().isBlank() || binding.etFBContact.text.toString().trim().length != 10) {
            binding.etFBContact.error = "Please enter a valid phone number"
            isCorrect = false
        }
        if (binding.etFBDifficultyFaced.text.toString().isEmpty() || binding.etFBDifficultyFaced.text.toString().isBlank()) {
            binding.etFBDifficultyFaced.error = "Please don't provide the empty input for diffculty faced section"
            isCorrect = false
        }
        if (binding.etFBSuggestion.text.toString().isEmpty() || binding.etFBSuggestion.text.toString().isBlank()) {
            binding.etFBSuggestion.error = "Please don't provide the empty input for suggestions section"
            isCorrect = false
        }

        return isCorrect
    }

    private fun getFeedbackData(): FeedbackModel {
        return FeedbackModel(
            UUID.randomUUID().toString(),
            binding.etFBContact.text.toString().trim(),
            binding.etFBDifficultyFaced.text.toString().trim(),
            binding.etFBSuggestion.text.toString().trim(),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FEEDBACK_COLLECTION = "feedback"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedbackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeedbackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
