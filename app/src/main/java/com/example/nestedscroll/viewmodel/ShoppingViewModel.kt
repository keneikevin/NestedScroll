package com.example.nestedscroll.viewmodel
import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.nestedscroll.data.Item
import com.example.nestedscroll.data.ItemDataBase
import com.example.nestedscroll.data.ItemRepository
import com.example.nestedscroll.other.Constants.PAGE_SIZE
import com.example.nestedscroll.other.Event
import com.example.nestedscroll.other.Resource
import com.example.nestedscroll.pagingsource.CakePagingSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class ShoppingViewModel(application: Application) : AndroidViewModel(application) {
    val repository: ItemRepository
    val allItems:LiveData<List<Item>>
    init {
        val dao = ItemDataBase.getDatabase(application).getItemsDao()
        repository = ItemRepository(dao)
        allItems = repository.allItems
    }
    private val KEY = "Saved_Shopping_List"


    private val _cake = MutableLiveData<Event<Resource<Item>>>()
    val cake: LiveData<Event<Resource<Item>>> = _cake

    private val _curPrice = MutableLiveData<String>()
    val curPrice: LiveData<String> get() = _curPrice
    // The current cost



    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = 1500
    }

    fun setCurPrice(pr:String){
        _curPrice.value = pr
    }

    fun calculate(sz: String){
        val prc = _curPrice.value?.toInt()
        var p = sz.toInt()
        var s= p * prc!!
        _score.value = s
    }


    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<Item>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<Item>>> = _insertShoppingItemStatus


    val flow = Pager(PagingConfig(PAGE_SIZE)){
        CakePagingSource(FirebaseFirestore.getInstance())
    }.flow.cachedIn(viewModelScope)




    fun insertShoppingItemIntoDb(shoppingItem: Item) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, size: String, price: String,img:String) {
        if (name.isEmpty() || size.isEmpty() || price.isEmpty()){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        val shoppingItem = Item(name,price.toInt(),img)
        insertShoppingItemIntoDb(shoppingItem)
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }


}

