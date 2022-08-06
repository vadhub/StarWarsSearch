package com.vad.starwarssearch.domain

sealed class Resource {
    abstract fun handle(handleResult: HandleResult)

    class Success(): Resource() {
        override fun handle(handleResult: HandleResult) {
            handleResult.handleSuccess()
        }
    }

    class Error(private val message: String): Resource() {
        override fun handle(handleResult: HandleResult) {
            handleResult.handleError(message)
        }
    }
}