package com.example.nestedscroll.viewmodel
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.nestedscroll.data.Cake
import com.example.nestedscroll.other.Constants.PAGE_SIZE
import com.example.nestedscroll.other.Event
import com.example.nestedscroll.other.Resource
import com.example.nestedscroll.pagingsource.CakePagingSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class ShoppingViewModel : ViewModel() {

    private val KEY = "Saved_Shopping_List"


    private val _cake = MutableLiveData<Event<Resource<Cake>>>()
    val cake: LiveData<Event<Resource<Cake>>> = _cake

    private val _curPrice = MutableLiveData<String>()
    val curPrice: LiveData<String> get() = _curPrice
    // The current cost



    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = 1500
    }



        val flow = Pager(PagingConfig(PAGE_SIZE)){
            CakePagingSource(FirebaseFirestore.getInstance())
        }.flow.cachedIn(viewModelScope)




}

