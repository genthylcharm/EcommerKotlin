package com.example.appecommer.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appecommer.Model.CategoryModel
import com.example.appecommer.Model.SliderModel
import com.example.appecommer.Model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _recommended = MutableLiveData<MutableList<ItemsModel>>()


    val banners: LiveData<List<SliderModel>> = _banner
    val categories: LiveData<MutableList<CategoryModel>> = _category
    val recommended: LiveData<MutableList<ItemsModel>> = _recommended


    fun loadFiltered(id:String) {
        val Ref = firebaseDatabase.getReference("Items")
        val query: Query = Ref.orderByChild("categoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSapshot in snapshot.children) {
                    val list = childSapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)

                    }

                }
                // Cập nhật LiveData với danh sách itemsModel mới
                _recommended.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error loading loadFiltered items: ${error.message}")
            }
        })
    }

    fun loadRecommended() {
        val Ref = firebaseDatabase.getReference("Items")
        val query: Query = Ref.orderByChild("showRecommended").equalTo(true)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSapshot in snapshot.children) {
                    val list = childSapshot.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list)

                    }

                }
                // Cập nhật LiveData với danh sách itemsModel mới
                _recommended.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DatabaseError", "Error loading recommended items: ${error.message}")
            }
        })
    }
    fun loadCategory() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                // Duyệt qua từng con của DataSnapshot
                for (categorySnapshot in snapshot.children) {
                    // Chuyển đổi DataSnapshot thành CategoryModel
                    val categoryItem = categorySnapshot.getValue(CategoryModel::class.java)
                    // Kiểm tra nếu categoryItem không null thì thêm vào danh sách
                    categoryItem?.let { lists.add(it) }
                }
                // Cập nhật LiveData với danh sách CategoryModel mới
                _category.postValue(lists)
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi khi không thể tải dữ liệu từ Firebase
                println("Error loading Category: ${error.message}")
            }

        })
    }


    // Hàm load dữ liệu banner từ Firebase
    fun loadBanners() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                // Duyệt qua từng con của DataSnapshot
                for (bannerSnapshot in snapshot.children) {
                    // Chuyển đổi DataSnapshot thành SliderModel
                    val sliderItem = bannerSnapshot.getValue(SliderModel::class.java)
                    // Kiểm tra nếu sliderItem không null thì thêm vào danh sách
                    sliderItem?.let { lists.add(it) }
                }
                // Cập nhật LiveData với danh sách SliderModel mới
                _banner.postValue(lists)
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi khi không thể tải dữ liệu từ Firebase
                println("Error loading banners: ${error.message}")
            }

        })
    }
}