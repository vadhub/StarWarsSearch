package com.vad.starwarssearch.domain

interface HandleResult {
    fun handleSuccess()
    fun handleError(error: String)
}