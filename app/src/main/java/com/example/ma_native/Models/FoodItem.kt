package com.example.ma_native.Models

import android.net.Uri
import com.example.ma_native.R

class FoodItem(
    name: String,
    ingredients: String = "N/A",
    price: Int? = null,
    img: Uri = Uri.parse("android.resource://com.example.ma_native/" + R.drawable.no_img),
) : BaseItem(name, ingredients, price, img){}
