package dev.jorik.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun listen() : Flow<List<Todo>>

    @Insert
    suspend fun add(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}