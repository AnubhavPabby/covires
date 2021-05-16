package com.trippyheads.covires.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.trippyheads.covires.data.models.HelpRequestModel
import com.trippyheads.covires.data.models.ProvideResourcesModel
import com.trippyheads.covires.databinding.FragmentHelpRequestsBinding
import com.trippyheads.covires.ui.adapters.HelpRequestsAdapter
import com.trippyheads.covires.ui.adapters.KnowAboutResourcesAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HelpRequests.newInstance] factory method to
 * create an instance of this fragment.
 */
class HelpRequests : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHelpRequestsBinding? = null
    private val binding get() = _binding!!

    private val firestore by lazy {
        Firebase.firestore
    }

    private lateinit var adapter: HelpRequestsAdapter

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
        _binding = FragmentHelpRequestsBinding.inflate(inflater, container, false)

        val query: Query = firestore
            .collection(HELP_REQUEST_COLLECTION)
            .orderBy(TIMESTAMP_FIELD)

        val options: FirestoreRecyclerOptions<HelpRequestModel> = FirestoreRecyclerOptions.Builder<HelpRequestModel>()
            .setQuery(query, HelpRequestModel::class.java)
            .build()

        setupResourcesList(options)

        return binding.root
    }

    private fun setupResourcesList(options: FirestoreRecyclerOptions<HelpRequestModel>) {
        binding.rvResourceProviders.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        adapter = HelpRequestsAdapter(options)
        if(::adapter.isInitialized) {
            binding.rvResourceProviders.adapter = adapter
        }
    }

    override fun onStart() {
        super.onStart()
        if(::adapter.isInitialized) {
            adapter.startListening()
        }
    }

    override fun onStop() {
        super.onStop()
        if(::adapter.isInitialized) {
            adapter.stopListening()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val HELP_REQUEST_COLLECTION = "help_requests"
        private const val TIMESTAMP_FIELD = "requestHelpDate"


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnythingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HelpRequests().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}