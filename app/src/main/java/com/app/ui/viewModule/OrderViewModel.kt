package com.app.ui.viewModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.data.model.FoodMenu

class OrderViewModel : ViewModel() {
    var _item : MutableLiveData<FoodMenu.Category.Details.FoodItem> = MutableLiveData()
    val item : LiveData<FoodMenu.Category.Details.FoodItem> get() = _item
    val _totalGST:MutableLiveData<Float> = MutableLiveData()
    val totalGST:LiveData<Float> get() = _totalGST
    val _totalAmountGST:MutableLiveData<Float> = MutableLiveData()
    val totalAmountGST:LiveData<Float> get() = _totalAmountGST

}