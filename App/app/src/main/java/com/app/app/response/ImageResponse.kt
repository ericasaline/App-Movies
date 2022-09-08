package com.app.app.response

import com.app.app.model.ImageModel
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("backdrops")
    val images: List<ImageModel>
)