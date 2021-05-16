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
import com.trippyheads.covires.data.models.ProvideResourcesModel
import com.trippyheads.covires.databinding.FragmentProvideResourcesFormBinding
import timber.log.Timber
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProvideResourcesFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProvideResourcesFormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentProvideResourcesFormBinding? = null
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
        _binding = FragmentProvideResourcesFormBinding.inflate(inflater, container, false)

        binding.etResProvFullName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvFullName.text.toString().isEmpty() || binding.etResProvFullName.text.toString().isBlank()){
                    binding.etResProvFullName.error = "Please don't provide empty input for the Fullname"
                }
            }
        })

        binding.etResProvContact.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvContact.text.toString().isEmpty() || binding.etResProvContact.text.toString().isBlank() || binding.etResProvContact.text.toString().length != 10) {
                    binding.etResProvContact.error = "Please provide a valid phone number"
                }
            }
        })

        binding.etResProvState.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvState.text.toString().isEmpty() || binding.etResProvState.text.toString().isBlank()) {
                    binding.etResProvState.error = "Please don't provide empty input for the State"
                }
            }
        })

        binding.etResProvCity.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvCity.text.toString().isEmpty() || binding.etResProvCity.text.toString().isBlank()) {
                    binding.etResProvCity.error = "Please don't provide empty input for the City"
                }
            }
        })

        binding.etResProvPincode.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvPincode.text.toString().isEmpty() || binding.etResProvPincode.text.toString().isBlank()) {
                    binding.etResProvPincode.error = "Please don't provide empty input for the Pincode"
                }
            }
        })

        binding.etResProvDistrict.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvDistrict.text.toString().isEmpty() || binding.etResProvDistrict.text.toString().isBlank()) {
                    binding.etResProvDistrict.error = "Please don't provide empty input for the District"
                }
            }
        })

        binding.etResProvResType.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvResType.text.toString().isEmpty() || binding.etResProvResType.text.toString().isBlank()) {
                    binding.etResProvResType.error = "Please don't provide empty input for the Resource Type"
                }
            }
        })

        binding.etResProvResDesc.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.etResProvResDesc.text.toString().isEmpty() || binding.etResProvResDesc.text.toString().isBlank()) {
                    binding.etResProvResDesc.error = "Please don't provide empty input for the Resource Description"
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResProvSubmit.setOnClickListener {
            if (!checkWhetherFormIsCorrectlyFilled()) {
                return@setOnClickListener
            }

            val providerForGivenResource = getProviderForGivenResource()

            uploadProviderForGivenResource(providerForGivenResource)
        }
    }

    private fun uploadProviderForGivenResource(provideResources: ProvideResourcesModel) {
        firestore
            .collection(RESOURCE_PROVIDER_COLLECTION)
            .document(provideResources.providerId)
            .set(provideResources)
            .addOnSuccessListener {
                Timber.d("Provider Resource Document successfully uploaded in firestore!")

                Snackbar.make(
                    binding.btnResProvSubmit,
                    "Your given resource as a provider is successfully uploaded",
                    Snackbar.LENGTH_LONG
                ).show()

            }
            .addOnFailureListener { error ->
                Timber.w("Error updating provider resource document ${error.printStackTrace()}")

                Snackbar.make(
                    binding.btnResProvSubmit,
                    "Error Occured while uploading you as provider for given resource.Please try again",
                    Snackbar.LENGTH_LONG
                ).show()
            }
    }

    private fun checkWhetherFormIsCorrectlyFilled(): Boolean {
        var isCorrect = true

        if (binding.etResProvFullName.text.toString().isEmpty() || binding.etResProvFullName.text.toString().isBlank()){
            binding.etResProvFullName.error = "Please don't provide empty input for the Fullname"
            isCorrect = false
        }
        if (binding.etResProvContact.text.toString().isEmpty() || binding.etResProvContact.text.toString().isBlank() || binding.etResProvContact.text.toString().length != 10) {
            binding.etResProvContact.error = "Please provide a valid phone number"
            isCorrect = false
        }
        if (binding.etResProvState.text.toString().isEmpty() || binding.etResProvState.text.toString().isBlank()) {
            binding.etResProvState.error = "Please don't provide empty input for the State"
            isCorrect = false
        }
        if (binding.etResProvCity.text.toString().isEmpty() || binding.etResProvCity.text.toString().isBlank()) {
            binding.etResProvCity.error = "Please don't provide empty input for the City"
            isCorrect = false
        }
        if (binding.etResProvPincode.text.toString().isEmpty() || binding.etResProvPincode.text.toString().isBlank()) {
            binding.etResProvPincode.error = "Please don't provide empty input for the Pincode"
        }
        if (binding.etResProvDistrict.text.toString().isEmpty() || binding.etResProvDistrict.text.toString().isBlank()) {
            binding.etResProvDistrict.error = "Please don't provide empty input for the District"
            isCorrect = false
        }
        if (binding.etResProvResType.text.toString().isEmpty() || binding.etResProvResType.text.toString().isBlank()) {
            binding.etResProvResType.error = "Please don't provide empty input for the Resource Type"
        }
        if (binding.etResProvResDesc.text.toString().isEmpty() || binding.etResProvResDesc.text.toString().isBlank()) {
            binding.etResProvResDesc.error = "Please don't provide empty input for the Resource Description"
            isCorrect = false
        }

        return isCorrect
    }

    private fun getProviderForGivenResource(): ProvideResourcesModel {

        var email:String? = null
        if(!binding.etResProvEmail.text.toString().trim().isEmpty()) {
            email = binding.etResProvEmail.text.toString().trim()
        }

        var website:String? = null
        if(!binding.etResProvWeb.text.toString().trim().isEmpty()) {
            website = binding.etResProvWeb.text.toString().trim()
        }

        return ProvideResourcesModel(
            UUID.randomUUID().toString(),
            binding.etResProvFullName.text.toString().trim(),
            email,
            website,
            binding.etResProvContact.text.toString().trim(),
            binding.etResProvState.text.toString().trim(),
            binding.etResProvCity.text.toString().trim(),
            binding.etResProvPincode.text.toString().trim(),
            binding.etResProvDistrict.text.toString().trim(),
            binding.etResProvResType.text.toString().trim(),
            binding.etResProvResDesc.text.toString().trim(),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val RESOURCE_PROVIDER_COLLECTION = "resource_providers"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProvideResourcesFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProvideResourcesFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}