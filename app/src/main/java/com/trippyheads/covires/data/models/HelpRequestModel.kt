package com.trippyheads.covires.data.models

import com.google.firebase.Timestamp
import java.util.Date

class HelpRequestModel {

    lateinit var requestHelpId: String
    lateinit var requestHelpFullName: String
    var requestHelpEmailId: String? = null
    lateinit var requestHelpContact: String
    lateinit var requestHelpState: String
    lateinit var requestHelpCity: String
    lateinit var requestHelpPincode: String
    lateinit var requestHelpDistrict: String
    lateinit var requestHelpResourceType: String
    lateinit var requestHelpResourceTypeResourceDescription: String
    lateinit var requestHelpDate: Timestamp

    // Needed for firebase
    constructor() {

    }

    constructor(
        requestHelpId: String,
        requestHelpFullName: String,
        requestHelpEmailId: String?,
        requestHelpContact: String,
        requestHelpState: String,
        requestHelpCity: String,
        requestHelpPincode: String,
        requestHelpDistrict: String,
        requestHelpResourceType: String,
        requestHelpResourceTypeResourceDescription: String
    ) : this() {
        this.requestHelpId = requestHelpId
        this.requestHelpFullName = requestHelpFullName
        this.requestHelpEmailId = requestHelpEmailId
        this.requestHelpContact = requestHelpContact
        this.requestHelpState = requestHelpState
        this.requestHelpCity = requestHelpCity
        this.requestHelpPincode = requestHelpPincode
        this.requestHelpDistrict = requestHelpDistrict
        this.requestHelpResourceType = requestHelpResourceType
        this.requestHelpResourceTypeResourceDescription = requestHelpResourceTypeResourceDescription
        this.requestHelpDate = Timestamp(Date())
    }
}