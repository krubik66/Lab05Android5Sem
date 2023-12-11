package com.example.lab05

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {

    @Upsert
    fun upsertItem(products: DatabaseItem)

    @Delete
    fun deleteItem(products: DatabaseItem)

//    @Query("SELECT * FROM meal_details WHERE is_in_cart")
//    fun getShoppingMeals(): Flow<List<MealDetails>>

    @Query("SELECT * FROM item_table WHERE id = :id")
    fun getItemWithId(id: String): Flow<DatabaseItem?>

    @Query("DELETE FROM item_table WHERE id = :id")
    fun deleteItemWithId(id: String)

    @Query("SELECT * FROM item_table")
    fun getItems(): MutableList<DatabaseItem>?

    @Query("SELECT * FROM item_table WHERE isChecked = 1")
    fun getChecked(): MutableList<DatabaseItem>?

    @Query("UPDATE item_table SET isChecked = :isChecked WHERE id = :itemId")
    fun updateItemCheckedStatus(itemId: Int, isChecked: Boolean)

    @Query("UPDATE item_table SET text_name = :name, text_spec = :spec, " +
            "item_strength = :strength, dangerous = :danger WHERE id = :itemId")
    fun updateItem(itemId: Int, name: String, spec:String, strength:Float, danger:Boolean)

}