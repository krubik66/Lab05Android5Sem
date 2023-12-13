package com.example.lab05

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(private val repository: ListRepository) : ViewModel() {

    private val _items = MutableLiveData<List<DatabaseItem>>()
    val items: LiveData<List<DatabaseItem>> get() = _items

    private val _itemsChecked = MutableLiveData<List<DatabaseItem>>()
    val itemsChecked: LiveData<List<DatabaseItem>> get() = _itemsChecked

    init {
        viewModelScope.launch {
            _items.value = repository.getAllItems()
            _itemsChecked.value = repository.getChecked()
        }
    }

    fun addItem(item: DatabaseItem) {
        viewModelScope.launch {
            repository.upsertItem(item)
            _items.value = repository.getAllItems()
        }
    }

    fun updateItem(itemId: Int, name: String, spec: String, strength: Float, danger: Boolean) {
        viewModelScope.launch {
            repository.updateItem(itemId, name, spec, strength, danger)
            _items.value = repository.getAllItems()
        }
    }

    fun deleteItem(item: DatabaseItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
            _items.value = repository.getAllItems()
        }
    }

    fun checkItem(id: Int, checked: Boolean) {
        viewModelScope.launch {
            repository.updateChecked(id, checked)
            _itemsChecked.value = repository.getChecked()
        }
    }

    fun deleteItemById(id: Int) {
        viewModelScope.launch {
            repository.deleteWithId(id)
            _items.value = repository.getAllItems()
        }
    }

}