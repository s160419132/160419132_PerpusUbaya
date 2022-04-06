package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.model.Histori
import com.example.a160419132_perpusubaya.util.GlobalData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class HistoriViewModel(application: Application): AndroidViewModel(application) {
    private var TAG ="historyTag"
    private var queue: RequestQueue?=null

    val HistoryLD= MutableLiveData<List<Histori>>()
    val loadingLD= MutableLiveData<Boolean>()
    val loadingErrorLD= MutableLiveData<Boolean>()

    fun gethistory(){
        loadingErrorLD.value= false
        loadingLD.value= true

        queue= Volley.newRequestQueue(getApplication())
        var url="https://gist.githubusercontent.com/s160419132/afd95c6d369e597b364e19f48785b724/raw/ae5d2cf41e2c6271ea3bc93e9b715e8d92cce594/Riwayat.json"
        val stringRequest =  StringRequest(
            Request.Method.GET,url,
            {response->
                val data= JSONObject(response).getJSONArray("riwayat").toString()
                val sType= object : TypeToken<ArrayList<Histori>>(){}.type
                val result= Gson().fromJson<ArrayList<Histori>>(data,sType)
                HistoryLD.value=result
                loadingLD.value=false
                Log.d("showhistoryresult",result.toString())
            },
            {
                loadingLD.value=false
                loadingErrorLD.value=true
                Log.d("howhistoryresult", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}