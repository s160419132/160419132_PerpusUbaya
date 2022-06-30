package com.example.a160419132_perpusubaya.model

import androidx.room.*

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAll(vararg login: Login)



    @Query("SELECT * From login")
    suspend fun  selectAlluser(): List<Login>

    @Query("SELECT * From login WHERE nrp=:nrp AND password=:pwd")
    suspend fun checkLogin(nrp:String,pwd:String):Login

    @Query("SELECT * From login WHERE nrp=:nrp")
    suspend fun getnama(nrp:String):Login


    @Delete
    suspend fun deleteLogin(login: Login)
}

@Dao
interface BookDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookInsertAll(vararg book: Book)

    @Query("SELECT * From book")
    suspend fun selectAllBook(): List<Book>

    @Query("UPDATE Book SET deskripsi=:deskripsi WHERE isbn=:isbn")
    suspend fun updateBook(deskripsi:String, isbn:String)

    @Delete
    suspend fun deleteBook(book: Book)
}
@Dao
interface HistoryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun historyInsertAll(vararg histori: Histori)

    @Query("SELECT * From histori")
    suspend fun selectAllHistory(): List<Histori>

    @Delete
    suspend fun deleteBook(histori: Histori)
}

@Dao
interface CategoryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun categoryInsertAll(vararg category: Category)

    @Query("SELECT * From category")
    suspend fun selectAllCategory(): List<Category>

    @Delete
    suspend fun deleteCategory(category: Category)
}

@Dao
interface ReviewDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun reviewInsertAll(vararg review: Review)

    @Query("SELECT * From review")
    suspend fun selectAllReview(): List<Review>

    @Query("SELECT * FROM review WHERE book_isbn=:isbn ORDER BY id_Review DESC")
    suspend fun selectReviewbyisbn(isbn:String): List<Review>

    @Delete
    suspend fun deleteReview(review: Review)
}