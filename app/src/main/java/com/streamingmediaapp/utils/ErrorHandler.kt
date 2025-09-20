package com.streamingmediaapp.utils

import android.content.Context
import android.widget.Toast
import com.streamingmediaapp.R

object ErrorHandler {
    
    fun handleError(context: Context, error: Throwable, showToast: Boolean = true) {
        Logger.e("Error occurred", error)
        
        if (showToast) {
            val message = when (error) {
                is java.net.UnknownHostException -> context.getString(R.string.error_no_internet)
                is java.net.SocketTimeoutException -> context.getString(R.string.error_timeout)
                is java.io.IOException -> context.getString(R.string.error_network)
                else -> context.getString(R.string.error_generic)
            }
            
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    
    fun handleApiError(context: Context, errorCode: Int, showToast: Boolean = true) {
        Logger.e("API Error: $errorCode")
        
        if (showToast) {
            val message = when (errorCode) {
                400 -> context.getString(R.string.error_bad_request)
                401 -> context.getString(R.string.error_unauthorized)
                403 -> context.getString(R.string.error_forbidden)
                404 -> context.getString(R.string.error_not_found)
                500 -> context.getString(R.string.error_server_error)
                else -> context.getString(R.string.error_generic)
            }
            
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
