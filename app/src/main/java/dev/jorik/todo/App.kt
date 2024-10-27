package dev.jorik.todo

import android.app.Application
import androidx.room.Room

class App :Application() {

    companion object {
        lateinit var db :AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database.db"
        ).build()
    }
}