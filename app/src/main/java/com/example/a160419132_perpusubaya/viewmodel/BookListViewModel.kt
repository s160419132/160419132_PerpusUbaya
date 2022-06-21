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
import com.example.a160419132_perpusubaya.util.GlobalData
import com.example.a160419132_perpusubaya.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class BookListViewModel(application: Application): AndroidViewModel(application), CoroutineScope{

    private var TAG ="volleyTag"
    private var queue: RequestQueue?= null


    val BooksLD= MutableLiveData<List<Book>>()
    val loadingLD= MutableLiveData<Boolean>()
    private var job = Job()
    fun loadlist(){
        loadingLD.value= true
        launch {
            val db= buildDb(getApplication())
            BooksLD.value=db.bookDao().selectAllBook()
            loadingLD.value=false
        }
        /*
        queue= Volley.newRequestQueue(getApplication())
        var url="https://gist.githubusercontent.com/s160419132/7d561d8dd898f767c2ee6e79446870f2/raw/b5620c9563f7ef4859e8d1af82fb79d5afc603b1/Buku.json"
        val stringRequest =  StringRequest(
            Request.Method.GET,url,
            {response->
                val data= JSONObject(response).getJSONArray("buku").toString()
                val sType= object : TypeToken<ArrayList<Book>>(){}.type
                val result= Gson().fromJson<ArrayList<Book>>(data,sType)
                BooksLD.value=result
                GlobalData.booklist=result
                loadingLD.value=false
                Log.d("showvolley",result.toString())
            },
            {
                loadingLD.value=false
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)*/
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}