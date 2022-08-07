package com.vad.starwarssearch.domain

import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.entity.CharactersList

sealed class Result {
    abstract fun handle(handleResult: HandleResult)

    class Success(private val list: List<Character>): Result() {
        override fun handle(handleResult: HandleResult) {
            handleResult.handleSuccess(list)
        }
    }

    class Error(private val message: String): Result() {
        override fun handle(handleResult: HandleResult) {
            handleResult.handleError(message)
        }
    }
}