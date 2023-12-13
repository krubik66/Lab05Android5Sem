package com.example.lab05

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {

    @Upsert
    suspend fun upsertItem(products: DatabaseItem)

    @Delete
    suspend fun deleteItem(products: DatabaseItem)


//    @Query("SELECT * FROM item_table WHERE id = :id")
//    suspend fun getItemWithId(id: String): Flow<DatabaseItem?>

    @Query("DELETE FROM item_table WHERE id = :id")
    suspend fun deleteItemWithId(id: String)

    @Query("SELECT * FROM item_table")
    suspend fun getItems(): MutableList<DatabaseItem>?

    @Query("SELECT * FROM item_table WHERE isChecked = 1")
    suspend fun getChecked(): MutableList<DatabaseItem>?

    @Query("UPDATE item_table SET isChecked = :isChecked WHERE id = :itemId")
    suspend fun updateItemCheckedStatus(itemId: Int, isChecked: Boolean)

    @Query("UPDATE item_table SET text_name = :name, text_spec = :spec, " +
            "item_strength = :strength, dangerous = :danger WHERE id = :itemId")
    suspend fun updateItem(itemId: Int, name: String, spec:String, strength:Float, danger:Boolean)

}