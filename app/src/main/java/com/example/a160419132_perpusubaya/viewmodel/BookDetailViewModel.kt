package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.model.PerpusDatabase
import com.example.a160419132_perpusubaya.util.GlobalData
import com.example.a160419132_perpusubaya.util.buildDb
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class BookDetailViewModel(application: Application): AndroidViewModel(application),CoroutineScope{

    val BookLD= MutableLiveData<Book>()
    private var job= Job()
    fun getBookDetail(isbn:String){
        launch{
            val db= buildDb(getApplication())
            Log.d( "database listuser",db.loginDao().selectAlluser().toString())
            for (i in db.bookDao().selectAllBook()){
                if (isbn==i.isbn){
                    BookLD.value=i
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}