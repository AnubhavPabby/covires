package com.trippyheads.covires.data.models

import com.google.firebase.Timestamp
import java.util.Date

class ProvideResourcesModel {

    lateinit var providerId: String
    lateinit var providerFullName: String
    var providerEmailId: String? = null
    var providerWebsite: String? = null
    lateinit var providerContact: String
    lateinit var providerState: String
    lateinit var providerCity: String
    lateinit var providerPincode: String
    lateinit var providerDistrict: String
    lateinit var providerResourceType: String
    lateinit var providerResourceTypeResourceDescription: String
    lateinit var providerListingResourceDate: Timestamp

    // Required By Firebase
    constructor() {
    }

    constructor(
        providerId: String,
        providerFullName: String,
        providerEmailId: String?,
        providerWebsite: String?,
        providerContact: String,
        providerState: String,
        providerCity: String,
        providerPincode: String,
        providerDistrict: String,
        providerResourceType: String,
        providerResourceTypeResourceDescription: String,
    ) {
        this.providerId = providerId
        this.providerFullName = providerFullName
        this.providerEmailId = providerEmailId
        this.providerWebsite = providerWebsite
        this.providerContact = providerContact
        this.providerState = providerState
        this.providerCity = providerCity
        this.providerPincode = providerPincode
        this.providerDistrict = providerDistrict
        this.providerResourceType = providerResourceType
        this.providerResourceTypeResourceDescription = providerResourceTypeResourceDescription
        this.providerListingResourceDate = Timestamp(Date())
    }
}