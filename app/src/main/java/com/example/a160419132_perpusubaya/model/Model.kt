package com.example.a160419132_perpusubaya.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Book(
    @ColumnInfo(name="isbn")
    val isbn:String?,
    @ColumnInfo(name="judul")
    val judul:String?,
    @ColumnInfo(name="subjudul")
    val subjudul:String?,
    @ColumnInfo(name="penulis")
    val penulis:String?,
    @ColumnInfo(name="terbit")
    val terbit:String?,
    @ColumnInfo(name="penerbit")
    val penerbit:String?,
    @ColumnInfo(name="jumlah_hal")
    val jumlahHalaman:Int?,
    @ColumnInfo(name="deskripsi")
    val deskripsi:String?,
    @ColumnInfo(name="website")
    val website:String?,
    @ColumnInfo(name="urlImage")
    val urlImage:String?,
    @ColumnInfo(name="kategori")
    val kategori:String?
){
    @PrimaryKey(autoGenerate = true)
    var id_book:Int =0
}

@Entity
data class Login(
    @ColumnInfo(name="nrp")
    var nrp:String?,
    @ColumnInfo(name="password")
    var password:String?,
    @ColumnInfo(name="nama")
    var nama:String?
){
    @PrimaryKey(autoGenerate = true)
    var id_user:Int =0
}

@Entity
data class Histori(
    @ColumnInfo(name="book_isbn")
    val isbn: String?,
    @ColumnInfo(name="judul")
    val judul: String?,
    @ColumnInfo(name="penulis")
    val penulis: String?,
    @ColumnInfo(name="tglPinjam")
    val tglPinjam: String?,
    @ColumnInfo(name="urlGambar")
    val urlGambar: String?
){
    @PrimaryKey(autoGenerate = true)
    var id_history:Int =0
}

@Entity
data class Category(
    @ColumnInfo(name="nama")
    val nama:String?,
    @ColumnInfo(name="url_Ikon")
    @SerializedName("urlIkon")
    val url:String?
){
    @PrimaryKey(autoGenerate = true)
    var id_Category:Int =0
}

@Entity
data class Review(
    @ColumnInfo(name="book_isbn")
    var isbn: String?,
    @ColumnInfo(name="nama")
    var nama: String?,
    @ColumnInfo(name="komentar")
    var komentar:String?,
    @ColumnInfo(name="tanggal")
    var tanggal:String?
){
    @PrimaryKey(autoGenerate = true)
    var id_Review:Int =0
}
