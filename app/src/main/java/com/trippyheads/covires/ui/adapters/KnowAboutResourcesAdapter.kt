package com.trippyheads.covires.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.trippyheads.covires.data.models.ProvideResourcesModel
import com.trippyheads.covires.databinding.ItemResourceProviderBinding

class KnowAboutResourcesAdapter(options: FirestoreRecyclerOptions<ProvideResourcesModel>) : FirestoreRecyclerAdapter<ProvideResourcesModel, KnowAboutResourcesAdapter.ResourceViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val binding = ItemResourceProviderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ResourceViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ResourceViewHolder,
        position: Int,
        model: ProvideResourcesModel
    ) {
        holder.bindResourceItem(model)
    }

    class ResourceViewHolder(private val binding: ItemResourceProviderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindResourceItem(resourceProvider: ProvideResourcesModel) {
            binding.tvStateName.text = resourceProvider.providerState
            binding.tvCityName.text = resourceProvider.providerCity
            binding.tvDistrictName.text = resourceProvider.providerDistrict
            binding.tvPincodeNo.text = resourceProvider.providerPincode
            binding.tvContactNo.text = resourceProvider.providerContact
            binding.tvProviderName.text = resourceProvider.providerFullName
            binding.tvGivenResources.text = resourceProvider.providerResourceType
            binding.tvResourceDescription.text = resourceProvider.providerResourceTypeResourceDescription

            if(resourceProvider.providerEmailId != null) {
                binding.tvEmailId.text = resourceProvider.providerEmailId
            }
        }
    }
}