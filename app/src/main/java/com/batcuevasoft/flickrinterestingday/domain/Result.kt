package com.batcuevasoft.flickrinterestingday.domain

sealed class Result<out T>

data class Success<T>(val data: T) : Result<T>()
data class Error<T>(val errorDescription: String = "") : Result<T>()
data class UnknownError(val error: Exception = Exception(), val message: String? = null) : Result<Nothing>()