package com.example.ma_native

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.ma_native.Models.FoodItem
import com.example.ma_native.ViewModels.FoodItemViewModel
import com.example.ma_native.databinding.FragmentCreateItemSheetBinding

class CreateItemFragment : BottomSheetDialogFragment(){

    private lateinit var binding: FragmentCreateItemSheetBinding
    private lateinit var foodViewModel: FoodItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodViewModel = ViewModelProvider(requireActivity()).get(FoodItemViewModel::class.java)
        binding.createButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateItemSheetBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    private fun saveAction(){
        val name= binding.CreateFieldName.text.toString()
        val newFoodItem = FoodItem(name = name)
        foodViewModel.addFoodItem(newFoodItem)
        dismiss()
    }

}
