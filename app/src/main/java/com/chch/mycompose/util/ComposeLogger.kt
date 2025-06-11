package com.chch.mycompose.util

import android.util.Log

object ComposeLogger{
    fun logd(message: String){
        val tag = getCallFunctionName()
        Log.d(tag, message)
    }

    fun logi(message: String){
        val tag = getCallFunctionName()
        Log.i(tag, message)
    }

    fun logw(message: String){
        val tag = getCallFunctionName()
        Log.w(tag, message)
    }

    fun loge(message: String){
        val tag = getCallFunctionName()
        Log.e(tag, message)
    }

    fun loge(message: String, throwable: Throwable){
        val tag = getCallFunctionName()
        Log.e(tag, message, throwable)
    }

    fun getCallFunctionName() : String {
        return try {
            val stackTrace = Thread.currentThread().stackTrace
            //[0]: getCallerFunctionName [1]: logd/logi/logw/loge 함수 [2]: 실제 호출한 함수 (우리가 원하는 것)
            val callerElement = stackTrace.getOrNull(5) // 실제 호출 위치
            val methodName = callerElement?.methodName ?: "Unknown"

            if(methodName.length > 23){
                methodName.substring(0, 23)
            }else {
                methodName
            }
        } catch (e: Exception){
            "Unknown"
        }
    }
}

fun logd(message: String) = ComposeLogger.logd(message)
fun logi(message: String) = ComposeLogger.logi(message)
fun logw(message: String) = ComposeLogger.logw(message)
fun loge(message: String) = ComposeLogger.loge(message)
fun loge(message: String, throwable: Throwable) = ComposeLogger.loge(message, throwable)