package com.example.a160419132_perpusubaya.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Book(
    @ColumnInfo(name="isbn")
    var isbn:String?,
    @ColumnInfo(name="judul")
    var judul:String?,
    @ColumnInfo(name="subjudul")
    var subjudul:String?,
    @ColumnInfo(name="penulis")
    var penulis:String?,
    @ColumnInfo(name="terbit")
    var terbit:String?,
    @ColumnInfo(name="penerbit")
    var penerbit:String?,
    @ColumnInfo(name="jumlah_hal")
    var jumlahHalaman:Int?,
    @ColumnInfo(name="deskripsi")
    var deskripsi:String?,
    @ColumnInfo(name="website")
    var website:String?,
    @ColumnInfo(name="urlImage")
    var urlImage:String?,
    @ColumnInfo(name="kategori")
    var kategori:String?
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
