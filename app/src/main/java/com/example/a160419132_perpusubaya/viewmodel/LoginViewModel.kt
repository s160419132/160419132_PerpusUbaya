package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160419132_perpusubaya.model.Histori
import com.example.a160419132_perpusubaya.util.GlobalData
import com.example.a160419132_perpusubaya.model.Login
import com.example.a160419132_perpusubaya.model.PerpusDatabase
import com.example.a160419132_perpusubaya.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application):AndroidViewModel(application),CoroutineScope{
    var Login: Boolean = false
    var NRP=""
    var password=""
    val userLD = MutableLiveData<List<Login>>()
    private var job= Job()

    fun inputLogin(nrp:String,pwd:String):String{
        NRP=nrp
        password=pwd
        var result="tidakada"
        runBlocking {
            val db= buildDb(getApplication())
            Log.d( "database listuser",db.loginDao().selectAlluser().toString())
            for (i in db.loginDao().selectAlluser()){
                Log.d( "nrp $i",i.nrp.toString())
                if (NRP==i.nrp && password==i.password){
                    runBlocking {
                        result="ada"
                    }
                }
            }
        }
        return result
    }

    fun addUser(list:List<Login>){
        launch {
            val db= Room.databaseBuilder(getApplication(),PerpusDatabase::class.java,"Perpusdb").build()
            db.loginDao().InsertAll(*list.toTypedArray())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun clearTask(login: Login) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                PerpusDatabase::class.java, "newtododb").build()
            db.loginDao().deleteLogin(login)

            userLD.value=db.loginDao().selectAlluser()
        }
    }

}