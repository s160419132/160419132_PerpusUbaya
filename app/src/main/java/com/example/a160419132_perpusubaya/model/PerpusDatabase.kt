package com.example.a160419132_perpusubaya.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Login::class,Book::class,Histori::class,Category::class,Review::class),version = 1)
abstract class PerpusDatabase:RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun bookDao(): BookDao
    abstract fun historyDao(): HistoryDao
    abstract fun categoryDao(): CategoryDao
    abstract fun reviewDao(): ReviewDao

    companion object{
        @Volatile private var instance: PerpusDatabase?=null
        private val LOCK= Any()

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
                PerpusDatabase::class.java,"Perpusdb").build()

        private fun invoke(context: Context){
            if (instance!=null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance=it
                    }
                }
            }
        }


    }

}