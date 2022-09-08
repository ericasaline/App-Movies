package com.app.app.model

class CreditsModel(cast: CastModel?, crew: CrewModel?) {

    val name: String?
    val photo: String?
    val job: String?

    init {
        when(cast != null){
            true -> {
                name = cast.originalName
                photo = cast.profilePath
                job = cast.character
            }
            else -> {
                name = crew?.originalName
                photo = crew?.profilePath
                job = crew?.job
            }
        }
    }
}