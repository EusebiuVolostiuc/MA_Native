package com.example.ma_native.ViewModels

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ma_native.Models.FoodItem
import com.example.ma_native.Repositories.FoodRepository
import java.util.*

class FoodItemViewModel: ViewModel(){

    val cache = MutableLiveData<MutableList<FoodItem>>()

    private var foodRepo: FoodRepository

    init {
        foodRepo = FoodRepository()
        cache.value = foodRepo.getAll()
    }

    fun getById(itemId: UUID): FoodItem?{
        val list = cache.value
        return list!!.find{it.id == itemId}
    }

    fun addFoodItem(newItem: FoodItem)
    {
        val list = cache.value
        list!!.add(newItem)
        cache.postValue(list)
    }

    fun updateFoodItem(id: UUID, name: String?, ingredients: String?, price: Int?, @DrawableRes img: Uri?)
    {
        val list = cache.value
        val item = list!!.find{it.id == id}
        name?.let{item!!.name = it}
        ingredients?.let { item!!.ingredients = it }
        price?.let { item!!.price = it }
        img?.let { item!!.img = it }
        cache.postValue(list)
    }
    fun deleteFoodItem(itemId: UUID)
    {
        val list = cache.value
        val item = list!!.find{it.id == itemId}
        item?.let { list.remove(it) }
        cache.postValue(list)
    }
}