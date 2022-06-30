package com.example.a160419132_perpusubaya.view

import android.view.View
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.model.Login
import com.example.a160419132_perpusubaya.model.Review

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v:View)
}
interface ButtonBookListCategory{
    fun onButtonBookListCategory(v:View)
}
interface ButtonUlasanListener{
    fun onButtonUlasan(v:View)
}
interface ButtonSendReviewListener{
    fun onButtonSendReview(v:View,obj:String)
}
interface ButtonLoginListener{
    fun  onButtonLogin(v:View,nrp:String,password:String)
}
interface ButtonEdit{
    fun  onButtonEdit(v:View,obj: Book)
}