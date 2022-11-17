package com.example.ma_native

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ma_native.Adapters.ItemAdapter
import com.example.ma_native.Adapters.ItemClickListener
import com.example.ma_native.Repositories.FoodRepository
import com.example.ma_native.ViewModels.FoodItemViewModel
import com.example.ma_native.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var foodViewModel: FoodItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foodViewModel = ViewModelProvider(this).get(FoodItemViewModel::class.java)
        binding.newTaskButton.setOnClickListener(){
            CreateItemFragment().show(supportFragmentManager,"createItem")
        }

        setRecyclerView()
    }
    private fun setRecyclerView()
    {
        val mainActivity = this
        foodViewModel.cache.observe(this){
            binding.foodItemsList.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = ItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editFoodItem(itemId: UUID) {
        ItemDetailsFragment(itemId).show(supportFragmentManager,"editItem")
    }

}