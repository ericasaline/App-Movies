package com.app.app.model

import com.google.gson.annotations.SerializedName

data class CrewModel(
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    val job: String?
)