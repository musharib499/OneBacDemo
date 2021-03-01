package com.app.ui.viewModule

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.data.database.OrderHistroyListDao
import com.app.data.model.BookedOrder
import kotlinx.coroutines.launch

class HistoryViewModel @ViewModelInject constructor (private val orderHistroyListDao: OrderHistroyListDao)  : ViewModel() {

   private var _item:MutableLiveData<List<BookedOrder>> = MutableLiveData()
    val item:LiveData<List<BookedOrder>> get() = _item
    fun loadData(lifecycleOwner: LifecycleOwner){
        viewModelScope.launch {
            orderHistroyListDao.getHistoryList().observe(lifecycleOwner,{
                _item.value = it
            })
        }

    }
}