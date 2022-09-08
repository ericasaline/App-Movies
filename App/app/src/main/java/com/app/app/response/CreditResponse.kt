package com.app.app.response

import com.app.app.model.CastModel
import com.app.app.model.CrewModel

data class CreditResponse(
    val cast: List<CastModel>,
    val crew: List<CrewModel>
)