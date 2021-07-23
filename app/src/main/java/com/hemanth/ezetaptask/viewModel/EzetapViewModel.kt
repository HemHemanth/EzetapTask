package com.hemanth.ezetaptask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hemanth.ezetaptask.model.AndroidAssignment
import com.hemanth.ezetaptask.model.UiData
import com.hemanth.lib.NetworkInterface
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class EzetapViewModel: ViewModel() {
    fun getCustomUI(): LiveData<AndroidAssignment> {
        val customUiLiveData: MutableLiveData<AndroidAssignment> = MutableLiveData<AndroidAssignment>()
        viewModelScope.launch {
            val json = NetworkInterface().fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
            json.toString()

            val androidAssignment = Gson().fromJson(json.toString(), AndroidAssignment::class.java)
            customUiLiveData.value = androidAssignment
        }
        return customUiLiveData
    }
}