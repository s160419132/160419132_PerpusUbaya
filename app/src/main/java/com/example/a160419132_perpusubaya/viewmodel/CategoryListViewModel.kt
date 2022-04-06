package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419132_perpusubaya.model.Category
import com.example.a160419132_perpusubaya.model.Histori
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class CategoryListViewModel(application: Application): AndroidViewModel(application) {

    private var TAG="volleyTag"
    private var queue:RequestQueue ?= null

    var categoryLD=MutableLiveData<List<Category>>()
    fun getCategory(){


        queue= Volley.newRequestQueue(getApplication())
        var url="https://gist.githubusercontent.com/s160419132/1e815a9127aa7be7292e3c088f745c3f/raw/9e557e5c6d92460aa92a13773a1ae37e57f4b21b/kategori.json"
        val stringRequest =  StringRequest(
            Request.Method.GET,url,
            {response->
                val data= JSONObject(response).getJSONArray("kategori").toString()
                val sType= object : TypeToken<ArrayList<Category>>(){}.type
                val result= Gson().fromJson<ArrayList<Category>>(data,sType)
                categoryLD.value=result
                Log.d("showcategoryresult",result.toString())
            },
            {
                Log.d("showcategoryresult", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}