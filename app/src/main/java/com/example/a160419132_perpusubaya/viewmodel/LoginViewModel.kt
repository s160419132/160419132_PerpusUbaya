package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419132_perpusubaya.model.Histori
import com.example.a160419132_perpusubaya.util.GlobalData
import com.example.a160419132_perpusubaya.model.Login
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class LoginViewModel(application: Application):AndroidViewModel(application) {
    private var TAG ="volTag"
    private var queue: RequestQueue?= null

    var Login: Boolean = false
    var NRP=""
    var password=""
    val mahasiswaLD = ArrayList<Login>()
    fun inputLogin(nrp:String,pwd:String):String{
        NRP=nrp
        password=pwd
        var result="tidakada"
        for (i in GlobalData.logindata){
            if (NRP==i.nrp && password==i.password){
                result="ada"
            }
        }
        return result
    }
    fun LoginStatus(status:Boolean){
        Login=status
    }
    fun dataLogin(){
        var url="https://gist.githubusercontent.com/s160419132/e61c477737fa54f78684fd9c809c302d/raw/7bfbfa944e88fdbb8c3d5e5f0f196bb79ffebf91/login.json"
        queue = Volley.newRequestQueue(getApplication())
        var stringRequest = StringRequest(
            Request.Method.GET,url,
            {response->
                val data= JSONObject(response).getJSONArray("login").toString()
                val sType= object : TypeToken<ArrayList<Login>>(){}.type
                val result= Gson().fromJson<ArrayList<Login>>(data,sType)
                GlobalData.logindata=result
                Log.d("showChoose",result.toString())
            },
            {
                Log.d("showChoose", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}