package com.trippyheads.covires.data.models

import com.google.firebase.Timestamp
import java.util.Date

class FeedbackModel {
    lateinit var userFeedbackId: String
    lateinit var userContactNumber: String
    lateinit var userDifficultyFaced: String
    lateinit var userSuggestionsForApp: String
    lateinit var userFeedbackDate: Timestamp

    // Required By Firebase
    constructor() {
    }

    constructor(
        userFeedbackId: String,
        userContactNumber: String,
        userDifficultyFaced: String,
        userSuggestionsForApp: String
    ) : this() {
        this.userFeedbackId = userFeedbackId
        this.userContactNumber = userContactNumber
        this.userDifficultyFaced = userDifficultyFaced
        this.userSuggestionsForApp = userSuggestionsForApp
        this.userFeedbackDate = Timestamp(Date())
    }
}