package com.example.nestedscroll.pagingsource
import androidx.paging.PagingSource
import com.example.nestedscroll.data.Menu
import com.example.nestedscroll.other.Constants.CAKE_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class CakePagingSource(
    private val db: FirebaseFirestore,
    //  private val uid: String
) : PagingSource<QuerySnapshot, Menu>() {
    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Menu> {
        return try {
            val currentPage = params.key ?: db.collection(CAKE_COLLECTION)
                .get()
                .await()

            val lastDocumentSnapShot = currentPage.documents[currentPage.size() - 1]
            val nextPage = db.collection(CAKE_COLLECTION)
                .startAfter(lastDocumentSnapShot)
                .get()
                .await()

            LoadResult.Page(
                data = currentPage.toObjects(Menu::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}















