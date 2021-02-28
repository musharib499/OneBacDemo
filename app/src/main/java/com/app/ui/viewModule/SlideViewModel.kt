package com.app.ui.viewModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.data.model.FoodMenu

/**
 * Created by Musharib Ali on 25/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class SlideViewModel : ViewModel() {
    var _item : MutableLiveData<FoodMenu.Category> = MutableLiveData()
    val item : LiveData<FoodMenu.Category> get() = _item
}