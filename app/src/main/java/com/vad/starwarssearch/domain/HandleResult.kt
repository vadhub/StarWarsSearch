package com.vad.starwarssearch.domain

import com.vad.starwarssearch.data.entity.Character

interface HandleResult {
    fun handleSuccess(list: List<Character>)
    fun handleError(error: String)
}