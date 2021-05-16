package com.trippyheads.covires.data.models

import com.google.firebase.Timestamp
import java.util.Date

class FeedbackModel() {
    lateinit var feedbackId: String
    var contactNumber: String? = null
    lateinit var difficultyFaced: String
    lateinit var suggestionsForApp: String
    lateinit var date: Timestamp

    constructor(
        feedbackId: String,
        contactNumber: String?,
        difficultyFaced: String,
        suggestionsForApp: String
    ) : this() {
        this.feedbackId = feedbackId
        this.contactNumber = contactNumber
        this.difficultyFaced = difficultyFaced
        this.suggestionsForApp = suggestionsForApp
        this.date = Timestamp(Date())
    }
}