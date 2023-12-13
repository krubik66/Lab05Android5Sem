package com.example.lab05

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListRepository(
    context: Context
) {
    var dataList: MutableList<DatabaseItem>? = null
    lateinit var myDao: ListDao
    lateinit var myDb: AppDatabase

    init {
        myDb = AppDatabase.getDatabase(context)!!
        myDao = myDb.lstDao()!!
//        myDao.upsertItem(DatabaseItem())
//        myDao.upsertItem(DatabaseItem())

//        getList()

    }

    companion object {
        private var R_INSTANCE: ListRepository? = null

        fun getInstance(context: Context): ListRepository {
            if (R_INSTANCE == null) {
                R_INSTANCE = ListRepository(context)
            }
            return R_INSTANCE as ListRepository
        }
    }

    suspend fun upsertItem(item: DatabaseItem) = withContext(Dispatchers.IO) {
        myDao.upsertItem(item)
//        getList()
    }

    suspend fun deleteItem(item: DatabaseItem) = withContext(Dispatchers.IO) {
        myDao.deleteItem(item)
//        getList()
    }

    suspend fun deleteWithId(id: Int) = withContext(Dispatchers.IO) {
        myDao.deleteItemWithId(id.toString())
//        getList()
    }

    suspend fun getList() = withContext(Dispatchers.IO) {
        dataList =  myDao.getItems()
    }

    suspend fun getAllItems(): List<DatabaseItem>? = withContext(Dispatchers.IO) {
        return@withContext myDao.getItems()
    }

    suspend fun getChecked(): MutableList<DatabaseItem>? = withContext(Dispatchers.IO) {
        return@withContext myDao.getChecked()
    }

    suspend fun updateChecked(id: Int, checked: Boolean) = withContext(Dispatchers.IO) {
        myDao.updateItemCheckedStatus(id, checked)
    }

    suspend fun updateItem(itemId: Int, name: String, spec:String, strength:Float, danger:Boolean) = withContext(Dispatchers.IO) {
        myDao.updateItem(itemId, name, spec, strength, danger)
//        getList()
    }

}