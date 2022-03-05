package com.example.nestedscroll

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainActivityViewModel: ViewModel() {
    lateinit var List: MutableLiveData<MenuList>
    init {
        List = MutableLiveData()
    }

    fun getListObervable(): MutableLiveData<MenuList> {
        return List
    }

    fun loadData(context: Context) {
        val json = context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }

        val list = Gson().fromJson<MenuList>(json, MenuList::class.java)

        List.postValue(list)
    }
}