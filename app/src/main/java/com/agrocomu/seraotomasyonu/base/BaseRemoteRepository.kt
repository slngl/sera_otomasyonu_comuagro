package com.agrocomu.seraotomasyonu.base

import com.agrocomu.seraotomasyonu.entity.base.DataHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class BaseRepostory {

    suspend fun <T> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): DataHolder<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                if (response.isSuccessful) DataHolder.Success(response.body()!!)
                else {
                    DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> { //Network Error
                        DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                    }
                    is HttpException -> {
                        val code = throwable.code()
                        //val errorResponse = convertErrorBody(throwable)
                        DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                    }
                    else -> {
                        DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                    }
                }
            }
        }
    }

}