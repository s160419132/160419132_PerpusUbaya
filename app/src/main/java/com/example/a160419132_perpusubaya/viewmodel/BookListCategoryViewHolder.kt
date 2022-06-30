package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class BookListCategoryViewHolder(application: Application): AndroidViewModel(application),CoroutineScope {
    val BooksLD= MutableLiveData<List<Book>>()
    private var job=Job()
    fun getBookListCategory(Category:String){
          var hasil: ArrayList<Book>  = arrayListOf()
          runBlocking {
              val db= buildDb(getApplication())
              for ( i in db.bookDao().selectAllBook()){
                  if (i.kategori == Category){
                      hasil.add(i)
                  }
              }
          }
        BooksLD.value=hasil
        Log.d("berrr",hasil.toString())

    }

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}