package com.example.a160419132_perpusubaya.model

import com.google.gson.annotations.SerializedName

data class Book(
    val isbn:String?,
    val judul:String?,
    val subjudul:String?,
    val penulis:String?,
    val terbit:String?,
    val penerbit:String?,
    val jumlahHalaman:Int?,
    val deskripsi:String?,
    val website:String?,
    val urlImage:String?,
    val kategori:String?
)
data class Login(
    val nrp:String?,
    val password:String?,
    val nama:String?
)

data class Histori(
    val isbn: String?,
    val judul: String?,
    val penulis: String?,
    val tglPinjam: String?,
    val urlGambar: String?
)

data class Category(
    val nama:String?,
    @SerializedName("urlIkon")
    val url:String?
)

data class Review(
    val isbn: String?,
    val nama: String?,
    val komentar:String?,
    val tanggal:String?
)
