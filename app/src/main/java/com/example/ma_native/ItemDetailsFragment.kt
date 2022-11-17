package com.example.ma_native

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.ma_native.ViewModels.FoodItemViewModel
import com.example.ma_native.databinding.FragmentItemDetailsSheetBinding
import java.util.*

class ItemDetailsFragment(var itemId: UUID) : BottomSheetDialogFragment(){

    private lateinit var binding: FragmentItemDetailsSheetBinding
    private lateinit var foodViewModel: FoodItemViewModel

    private var newImgUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodViewModel = ViewModelProvider(requireActivity()).get(FoodItemViewModel::class.java)
        var item = foodViewModel.getById(itemId)
        item?.let {
            val editable = Editable.Factory.getInstance()
            binding.ItemDetailsName.text = editable.newEditable(it.name)
            binding.ItemDetailsIngredients.text = editable.newEditable(it.ingredients)
            binding.ItemDetailsPrice.text = editable.newEditable(it.price.toString())
            binding.ItemDetailsImage.setImageURI(it.img)
        }

        binding.saveChangesButton.setOnClickListener {
            saveAction(itemId)
        }
        binding.deleteButton.setOnClickListener{
            deleteAction(itemId)
        }
        binding.ItemDetailsImage.setOnClickListener{
            pickImage(binding)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItemDetailsSheetBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    private fun saveAction(itemId: UUID) {
        val name= binding.ItemDetailsName.text.toString()
        val ingredients = binding.ItemDetailsIngredients.text.toString()
        val price = Integer.valueOf(binding.ItemDetailsPrice.text.toString())
        foodViewModel.updateFoodItem(itemId,name,ingredients,price,newImgUri)
        dismiss()
    }

    private fun deleteAction(itemId: UUID) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Delete selected note from database
                foodViewModel.deleteFoodItem(itemId)
            }
            .setNegativeButton("No") { dialog, id ->
                // Dismiss the dialog
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
        dismiss()
    }

    private fun pickImage(binding: FragmentItemDetailsSheetBinding)
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1_000)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1_000 && resultCode == RESULT_OK) {
            binding.ItemDetailsImage.setImageURI(data?.data)
            newImgUri = data?.data
        }
    }

}
