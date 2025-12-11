package com.sundirect.customplayer

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData

class NetworkConnectionLiveData(private val context: Context) : LiveData<Boolean>() {

    var connectivityManager: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    private val networkCallback : NetworkCallback = object : NetworkCallback()
    {
        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(isNetworkAvailable())
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(isNetworkAvailable())
        }
    }

    override fun onActive() {
        super.onActive()
        postValue(isNetworkAvailable())
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }
    
    fun isNetworkAvailable() : Boolean
    {
        return connectivityManager?.let { it.getNetworkCapabilities(it.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true } ?: false
    }
}