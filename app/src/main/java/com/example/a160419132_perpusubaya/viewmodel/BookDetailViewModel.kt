package com.example.a160419132_perpusubaya.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.util.GlobalData

class BookDetailViewModel(application: Application): AndroidViewModel(application) {

    val BookLD= MutableLiveData<Book>()
    fun getBookDetail(isbn:String){

        for (i in GlobalData.booklist){
            if (i.isbn == isbn){
                BookLD.value=i
            }
        }
    }
}