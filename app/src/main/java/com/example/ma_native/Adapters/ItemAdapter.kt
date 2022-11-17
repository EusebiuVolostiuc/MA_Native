package com.example.ma_native.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ma_native.MainActivity
import com.example.ma_native.Models.BaseItem
import com.example.ma_native.Models.FoodItem
import com.example.ma_native.R
import com.example.ma_native.ViewModels.FoodItemViewModel
import com.example.ma_native.databinding.FoodItemCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class ItemAdapter(
    private val dataset: List<BaseItem>,
    private val clickListener: ItemClickListener
): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FoodItemCardBinding.inflate(from,parent,false)
        return ItemViewHolder(parent.context,binding,clickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

}

interface ItemClickListener{
    fun editFoodItem(itemId: UUID)
}
class ItemViewHolder(
    private val context: Context,
    private val binding: FoodItemCardBinding,
    private val clickListener: ItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bindItem(item: BaseItem)
    {
        binding.itemName.text = item.name
        binding.itemIngredients.text = item.ingredients
        binding.itemPrice.text = item.price?.toString() + " RON"
        binding.itemImage.setImageURI(item.img)

        binding.root.setOnClickListener{clickListener.editFoodItem(item.id)}
    }
}