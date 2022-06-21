package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.a160419132_perpusubaya.model.Login
import com.example.a160419132_perpusubaya.model.PerpusDatabase
import com.example.a160419132_perpusubaya.util.GlobalData
import com.example.a160419132_perpusubaya.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application):AndroidViewModel(application),CoroutineScope {

    private var job= Job()
    val UserLD= MutableLiveData<Login>()
    fun getNama(NRP:String){
        runBlocking {
            val db= buildDb(getApplication())
            Log.d( "database listuser",db.loginDao().selectAlluser().toString())
            for (i in db.loginDao().selectAlluser()){
                Log.d( "nrp $i",i.nrp.toString())
                if (NRP==i.nrp){
                    UserLD.value=i
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}