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
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.model.PerpusDatabase
import com.example.a160419132_perpusubaya.model.Review
import com.example.a160419132_perpusubaya.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class ReviewViewModel(application: Application): AndroidViewModel(application),CoroutineScope {

    val ReviewLD= MutableLiveData<List<Review>>()
    val loadingLD= MutableLiveData<Boolean>()
    private var job=Job()

    fun loadListReview(isbn:String){
        loadingLD.value= true
        launch {
            val db= buildDb(getApplication())
            ReviewLD.value=db.reviewDao().selectReviewbyisbn(isbn)
            loadingLD.value=false
        }

    }

    fun getnama(nrp:String):String{
        var nama=""
        runBlocking {
            val db= buildDb(getApplication())
            nama= db.loginDao().getnama(nrp).nama.toString()
        }
        return nama
    }

    fun insertReview(review:Review){
        if (review?.komentar != ""){
            runBlocking {
                val db= buildDb(getApplication())
                db.reviewDao().reviewInsertAll(review)
            }
        }

    }

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main

}