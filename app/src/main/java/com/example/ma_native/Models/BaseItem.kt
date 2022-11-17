package com.example.ma_native.Models

import android.net.Uri
import com.example.ma_native.R
import java.util.*

open class BaseItem(
    var name:String,
    var ingredients: String = "N/A",
    var price: Int? = null,
    var img: Uri = Uri.parse("android.resource://com.example.ma_native/" + R.drawable.no_img))
{
    val id: UUID = UUID.randomUUID()
}