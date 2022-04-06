package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.a160419132_perpusubaya.util.GlobalData

class ProfileViewModel(application: Application):AndroidViewModel(application) {

    fun getNama(NRP:String):String{
        var nama=""
        for (i in GlobalData.logindata){
            if (i.nrp ==NRP){
                nama= i.nama.toString()
            }
        }
        return nama
    }
}