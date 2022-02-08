package com.example.googlebooks.model.remote

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentActivity
import android.net.NetworkInfo



private const val TAG = "Network"
/** Extension function adding is connected
 * functionality to FragmentActivity Class
 * Syntax for extension function
 * fun[CLASS].funname(args:Any) :Unit
 */

fun FragmentActivity.isDeviceConnected(): Boolean {
    val connectivityManager = getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    return networkInfo?.isConnected ?: false
}


