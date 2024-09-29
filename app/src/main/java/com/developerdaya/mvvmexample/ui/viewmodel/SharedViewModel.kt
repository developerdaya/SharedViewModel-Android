package com.developerdaya.mvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developerdaya.mvvmexample.model.CountryListModel

class SharedViewModel : ViewModel() {
    private val _selectedData = MutableLiveData<ArrayList<CountryListModel.Data>?>()
    val selectedData: MutableLiveData<ArrayList<CountryListModel.Data>?> get() = _selectedData


       private val _selectedData2 = MutableLiveData<ArrayList<CountryListModel.Data>?>()
    val selectedData2: MutableLiveData<ArrayList<CountryListModel.Data>?> get() = _selectedData2




    fun select(data: ArrayList<CountryListModel.Data>) {
        _selectedData.value = data
    }

    //update New list
    fun updateNewList(newList: ArrayList<CountryListModel.Data>) {
        _selectedData2.value = newList
    }



    fun updateSelection(position: Int, isSelected: Boolean) {
        val currentData = _selectedData.value
        currentData?.get(position)?.isSelected = isSelected
        _selectedData.value = currentData
    }
}
