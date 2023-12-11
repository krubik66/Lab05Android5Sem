package com.example.lab05

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

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
//        dataList = runBlocking {
//            convertFlowToList(flow = getList())
//        }
        getList()
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

    fun upsertItem(item: DatabaseItem) {
        myDao.upsertItem(item)
        getList()
    }

    fun deleteItem(item: DatabaseItem) {
        myDao.deleteItem(item)
        getList()
    }

    fun deleteWithId(id: Int) {
        myDao.deleteItemWithId(id.toString())
        getList()
    }

    fun getList() {
        dataList =  myDao.getItems()
    }

    fun getchecked(): MutableList<DatabaseItem>? {
        return myDao.getChecked()
    }

    fun updateChecked(id: Int, checked: Boolean) {
        myDao.updateItemCheckedStatus(id, checked)
    }

    fun updateItem(itemId: Int, name: String, spec:String, strength:Float, danger:Boolean) {
        myDao.updateItem(itemId, name, spec, strength, danger)
        getList()
    }

    private suspend fun convertFlowToList(flow: Flow<List<DatabaseItem>>): MutableList<DatabaseItem>? {
        val resultList = mutableListOf<DatabaseItem>()

        flow.collect { item ->
            // Check if the item is not null before adding it to the list
            item?.let {
                resultList.addAll(it)
            }
        }

        return resultList
    }

}