package com.trippyheads.covires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.trippyheads.covires.data.models.HelpRequestModel
import com.trippyheads.covires.databinding.ItemHelpRequestBinding

class HelpRequestsAdapter(options: FirestoreRecyclerOptions<HelpRequestModel>) : FirestoreRecyclerAdapter<HelpRequestModel, HelpRequestsAdapter.RequestViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = ItemHelpRequestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RequestViewHolder,
        position: Int,
        model: HelpRequestModel
    ) {
        holder.bindHelpRequestItem(model)
    }

    class RequestViewHolder(private val binding: ItemHelpRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindHelpRequestItem(helpRequest: HelpRequestModel) {
            binding.tvCityName.text = helpRequest.requestHelpCity
            binding.tvStateName.text = helpRequest.requestHelpState
            binding.tvContactNo.text = helpRequest.requestHelpContact
            binding.tvDistrictName.text = helpRequest.requestHelpFullName
            binding.requirment.text = helpRequest.requestHelpResourceType
            binding.tvUpdatedAt.text = helpRequest.requestHelpDate.toDate().toString()
            binding.tvResourceDescription.text = helpRequest.requestHelpResourceTypeResourceDescription
        }
    }
}