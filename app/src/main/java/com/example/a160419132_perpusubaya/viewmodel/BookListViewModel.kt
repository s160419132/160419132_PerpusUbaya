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
import com.example.a160419132_perpusubaya.util.GlobalData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class BookListViewModel(application: Application): AndroidViewModel(application) {

    private var TAG ="volleyTag"
    private var queue: RequestQueue?= null


    val BooksLD= MutableLiveData<List<Book>>()
    val loadingLD= MutableLiveData<Boolean>()
    val loadingErrorLD= MutableLiveData<Boolean>()
    fun loadlist(){
        loadingErrorLD.value= false
        loadingLD.value= true

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
                loadingErrorLD.value=true
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}