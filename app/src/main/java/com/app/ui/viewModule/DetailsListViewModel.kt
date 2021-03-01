package com.app.ui.viewModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.data.model.FoodMenu

class DetailsListViewModel : ViewModel() {
    val _list: MutableLiveData<FoodMenu.Category> = MutableLiveData()
    val list: LiveData<FoodMenu.Category> get() = _list
    val _itemlist: MutableLiveData<FoodMenu.Category.Details> = MutableLiveData()
    val itemlist: LiveData<FoodMenu.Category.Details> get() = _itemlist
    val  _item : MutableLiveData<HashMap<Int,FoodMenu.Category.Details.FoodItem>> = MutableLiveData()
    val  item : LiveData<HashMap<Int,FoodMenu.Category.Details.FoodItem>> get() = _item



    }
