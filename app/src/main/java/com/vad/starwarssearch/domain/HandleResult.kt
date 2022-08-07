package com.vad.starwarssearch.domain

import com.vad.starwarssearch.data.entity.CharactersList

interface HandleResult {
    fun handleSuccess(list: CharactersList)
    fun handleError(error: String)
}