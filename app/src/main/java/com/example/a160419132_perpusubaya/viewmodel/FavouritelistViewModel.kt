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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class FavouritelistViewModel(application: Application): AndroidViewModel(application) {

    private var TAG ="volleyTag"
    private var queue: RequestQueue?= null


    val FavouritebooksLD= MutableLiveData<List<Book>>()
    val loadingLD= MutableLiveData<Boolean>()

    fun loadListFavourite(){
        loadingLD.value= true
        queue= Volley.newRequestQueue(getApplication())
        var url="https://gist.githubusercontent.com/s160419132/7eb4e3d3b7ee4097c266dd4d65d549e1/raw/3608a3582ae06f4963f050ee8a5af668a5829120/Favorite.json"
        val stringRequest =  StringRequest(
            Request.Method.GET,url,
            {response->
                val data= JSONObject(response).getJSONArray("favorite").toString()
                val sType= object : TypeToken<ArrayList<Book>>(){}.type
                val result= Gson().fromJson<ArrayList<Book>>(data,sType)
                FavouritebooksLD.value=result
                loadingLD.value=false
                Log.d("showvolleyvaforite",result.toString())
            },
            {
                loadingLD.value=false
                Log.d("showvolleyvaforite", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }
}