package com.example.ma_native.Repositories

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import com.example.ma_native.Models.FoodItem
import com.example.ma_native.R
import java.util.*

class FoodRepository {

    var buffer = MutableLiveData<MutableList<FoodItem>>()

    init {
        buffer.value = DefaultValues()
    }
    private fun DefaultValues():MutableList<FoodItem>{
        return mutableListOf<FoodItem>(
            FoodItem("Lasagna","meat, mozarella, tomato sauce",
                21, Uri.parse("android.resource://com.example.ma_native/" + R.drawable.lasagna_image)),
            FoodItem("Beef Burger","beef, cheddar, buns, onions",
                32, Uri.parse("android.resource://com.example.ma_native/" + R.drawable.hamburger_image))
            )
    }

    fun getAll(): MutableList<FoodItem>? {
        return buffer.value
    }
}