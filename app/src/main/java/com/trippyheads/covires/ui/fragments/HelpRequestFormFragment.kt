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
import com.trippyheads.covires.data.models.HelpRequestModel
import com.trippyheads.covires.databinding.FragmentHelpRequestFormBinding
import timber.log.Timber
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HelpRequestFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HelpRequestFormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHelpRequestFormBinding? = null
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
        _binding = FragmentHelpRequestFormBinding.inflate(inflater, container, false)

        binding.etReqFullName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqFullName.text.toString().isEmpty() || binding.etReqFullName.text.toString().isBlank()){
                    binding.etReqFullName.error = "Please don't provide empty input for the Fullname"
                }
            }
        })

        binding.etReqContact.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqContact.text.toString().isEmpty() || binding.etReqContact.text.toString().isBlank() || binding.etReqContact.text.toString().length != 10) {
                    binding.etReqContact.error = "Please provide a valid phone number"
                }
            }
        })

        binding.etReqState.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqState.text.toString().isEmpty() || binding.etReqState.text.toString().isBlank()) {
                    binding.etReqState.error = "Please don't provide empty input for the State"
                }
            }
        })

        binding.etReqCity.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqCity.text.toString().isEmpty() || binding.etReqCity.text.toString().isBlank()) {
                    binding.etReqCity.error = "Please don't provide empty input for the City"
                }
            }
        })

        binding.etReqPincode.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqPincode.text.toString().isEmpty() || binding.etReqPincode.text.toString().isBlank()) {
                    binding.etReqPincode.error = "Please don't provide empty input for the Pincode"
                }
            }
        })

        binding.etReqDistrict.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqDistrict.text.toString().isEmpty() || binding.etReqDistrict.text.toString().isBlank()) {
                    binding.etReqDistrict.error = "Please don't provide empty input for the District"
                }
            }
        })

        binding.etReqResType.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqResType.text.toString().isEmpty() || binding.etReqResType.text.toString().isBlank()) {
                    binding.etReqResType.error = "Please don't provide empty input for the Resource Type"
                }
            }
        })

        binding.etReqResDesc.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etReqResDesc.text.toString().isEmpty() || binding.etReqResDesc.text.toString().isBlank()) {
                    binding.etReqResDesc.error = "Please don't provide empty input for the Resource Description"
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReqSub.setOnClickListener {
            if (!checkWhetherFormIsCorrectlyFilled()) {
                return@setOnClickListener
            }

            val providerForGivenResource = getProviderForGivenResource()

            uploadProviderForGivenResource(providerForGivenResource)
        }
    }

    private fun uploadProviderForGivenResource(helpRequest: HelpRequestModel) {
        firestore
            .collection(HELP_REQUESTS_COLLECTION)
            .document(helpRequest.requestHelpId)
            .set(helpRequest)
            .addOnSuccessListener {
                Timber.d("Help Request Document successfully uploaded in firestore!")

                Snackbar.make(
                    binding.btnReqSub,
                    "Your given help request for the resource ${helpRequest.requestHelpResourceType} is successfully uploaded",
                    Snackbar.LENGTH_LONG
                ).show()

            }
            .addOnFailureListener { error ->
                Timber.w("Error uploading help request document ${error.printStackTrace()}")

                Snackbar.make(
                    binding.btnReqSub,
                    "Error occurred while uploading your request for resource of type ${helpRequest.requestHelpResourceType}.Please try again",
                    Snackbar.LENGTH_LONG
                ).show()
            }
    }

    private fun checkWhetherFormIsCorrectlyFilled(): Boolean {
        var isCorrect = true

        if (binding.etReqFullName.text.toString().isEmpty() || binding.etReqFullName.text.toString().isBlank()){
            binding.etReqFullName.error = "Please don't provide empty input for the Fullname"
            isCorrect = false
        }
        if (binding.etReqContact.text.toString().isEmpty() || binding.etReqContact.text.toString().isBlank() || binding.etReqContact.text.toString().length != 10) {
            binding.etReqContact.error = "Please provide a valid phone number"
            isCorrect = false
        }
        if (binding.etReqState.text.toString().isEmpty() || binding.etReqState.text.toString().isBlank()) {
            binding.etReqState.error = "Please don't provide empty input for the State"
            isCorrect = false
        }
        if (binding.etReqCity.text.toString().isEmpty() || binding.etReqCity.text.toString().isBlank()) {
            binding.etReqCity.error = "Please don't provide empty input for the City"
            isCorrect = false
        }
        if (binding.etReqPincode.text.toString().isEmpty() || binding.etReqPincode.text.toString().isBlank()) {
            binding.etReqPincode.error = "Please don't provide empty input for the Pincode"
        }
        if (binding.etReqDistrict.text.toString().isEmpty() || binding.etReqDistrict.text.toString().isBlank()) {
            binding.etReqDistrict.error = "Please don't provide empty input for the District"
            isCorrect = false
        }
        if (binding.etReqResType.text.toString().isEmpty() || binding.etReqResType.text.toString().isBlank()) {
            binding.etReqResType.error = "Please don't provide empty input for the Resource Type"
        }
        if (binding.etReqResDesc.text.toString().isEmpty() || binding.etReqResDesc.text.toString().isBlank()) {
            binding.etReqResDesc.error = "Please don't provide empty input for the Resource Description"
            isCorrect = false
        }

        return isCorrect
    }

    private fun getProviderForGivenResource(): HelpRequestModel {

        var email:String? = null
        if(!binding.etReqEmail.text.toString().trim().isEmpty()) {
            email = binding.etReqEmail.text.toString().trim()
        }

        return HelpRequestModel(
            UUID.randomUUID().toString(),
            binding.etReqFullName.text.toString().trim(),
            email,
            binding.etReqContact.text.toString().trim(),
            binding.etReqState.text.toString().trim(),
            binding.etReqCity.text.toString().trim(),
            binding.etReqPincode.text.toString().trim(),
            binding.etReqDistrict.text.toString().trim(),
            binding.etReqResType.text.toString().trim(),
            binding.etReqResDesc.text.toString().trim()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val HELP_REQUESTS_COLLECTION = "help_requests"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RequestHelpFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HelpRequestFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}