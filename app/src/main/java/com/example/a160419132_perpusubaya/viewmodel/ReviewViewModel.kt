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
import com.example.a160419132_perpusubaya.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class ReviewViewModel(application: Application): AndroidViewModel(application) {


    private var TAG ="volleyTag"
    private var queue: RequestQueue?= null


    val ReviewLD= MutableLiveData<List<Review>>()
    val loadingLD= MutableLiveData<Boolean>()

    fun loadListReview(){
        loadingLD.value= true
        queue= Volley.newRequestQueue(getApplication())
        var url="https://gist.githubusercontent.com/s160419132/7757ff0203be03137e3497d38090b149/raw/364bec3b1acdec45143374db9ea6162a8c22e6a7/Ulasan.json"
        val stringRequest =  StringRequest(
            Request.Method.GET,url,
            {response->
                val data= JSONObject(response).getJSONArray("ulasan").toString()
                val sType= object : TypeToken<ArrayList<Review>>(){}.type
                val result= Gson().fromJson<ArrayList<Review>>(data,sType)
                ReviewLD.value=result
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