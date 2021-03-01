package com.app.ui.viewModule

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.database.OrderHistroyListDao
import com.app.data.model.BookedOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel @ViewModelInject constructor (private val orderHistroyListDao: OrderHistroyListDao)  : ViewModel() {
    var _item : MutableLiveData<BookedOrder> = MutableLiveData()
    val item : LiveData<BookedOrder> get() = _item
    fun addItem(order: BookedOrder){ viewModelScope.launch(Dispatchers.IO) { orderHistroyListDao.insert(order) }}

}