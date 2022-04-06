package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.util.GlobalData

class BookListCategoryViewHolder(application: Application): AndroidViewModel(application) {
    val BooksLD= MutableLiveData<List<Book>>()

    fun getBookListCategory(Category:String){
          var hasil: ArrayList<Book>  = arrayListOf()
          for(i in GlobalData.booklist){
              if (i.kategori == Category){
                  hasil.add(i)
              }
          }

          BooksLD.value=hasil
        Log.d("berrr",hasil.toString())

    }
}