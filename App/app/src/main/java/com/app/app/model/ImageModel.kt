package com.app.app.model

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("file_path")
    val filePath: String?
)