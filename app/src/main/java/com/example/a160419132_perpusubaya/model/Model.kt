package com.example.a160419132_perpusubaya.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Book(
    @ColumnInfo(name="isbn")
    val isbn:String?,
    @ColumnInfo(name="judul")
    val judul:String?,
    @ColumnInfo(name= "subjudul")
    val subjudul:String?,
    @ColumnInfo(name = "penulis")
    val penulis:String?,
    @ColumnInfo(name = "terbit")
    val terbit:String?,
    @ColumnInfo(name = "penerbit")
    val penerbit:String?,
    @ColumnInfo(name = "jumlahHalaman")
    val jumlahHalaman:Int?,
    @ColumnInfo(name = "deskripsi")
    val deskripsi:String?,
    @ColumnInfo(name = "website")
    val website:String?,
    @ColumnInfo(name = "urlImage")
    val urlImage:String?,
    @ColumnInfo(name = "kategori")
    val kategori:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}

data class Login(
    @ColumnInfo(name = "nrp")
    val nrp:String?,
    @ColumnInfo(name = "password")
    val password:String?,
    @ColumnInfo(name = "nama")
    val nama:String?
){

}

data class Histori(
    @ColumnInfo(name = "isbn")
    val isbn: String?,
    @ColumnInfo(name = "judul")
    val judul: String?,
    @ColumnInfo(name = "penulis")
    val penulis: String?,
    @ColumnInfo(name = "tglPinjam")
    val tglPinjam: String?,
    @ColumnInfo(name = "urlGambar")
    val urlGambar: String?
)

data class Category(
    @ColumnInfo(name = "nama")
    val nama:String?,
    @SerializedName("urlIkon")
    val url:String?
)

data class Review(
    @ColumnInfo(name = "isbn")
    val isbn: String?,
    @ColumnInfo(name = "nama")
    val nama: String?,
    @ColumnInfo(name = "komentar")
    val komentar:String?,
    @ColumnInfo(name = "tanggal")
    val tanggal:String?
)

