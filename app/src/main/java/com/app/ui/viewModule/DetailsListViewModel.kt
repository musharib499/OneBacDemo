package com.app.ui.viewModule

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.data.model.FoodMenu

class DetailsListViewModel : ViewModel() {
    val _list: MutableLiveData<FoodMenu.Category> = MutableLiveData()
    val list: LiveData<FoodMenu.Category> get() = _list
    val _itemlist: MutableLiveData<FoodMenu.Category.Details> = MutableLiveData()
    val itemlist: LiveData<FoodMenu.Category.Details> get() = _itemlist
    val  _item : MutableLiveData<HashMap<Int,Int>> = MutableLiveData()
    val  item : LiveData<HashMap<Int,Int>> get() = _item



    }
