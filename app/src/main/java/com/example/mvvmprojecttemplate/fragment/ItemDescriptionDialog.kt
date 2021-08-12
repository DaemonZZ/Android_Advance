package com.example.mvvmprojecttemplate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mvvmprojecttemplate.databinding.ItemInfoBinding
import com.example.mvvmprojecttemplate.extension.getImageFromUrl
import com.example.mvvmprojecttemplate.model.Item
import com.example.mvvmprojecttemplate.model.ItemType
import android.R
import android.view.WindowManager


class ItemDescriptionDialog private constructor():DialogFragment(){
    private lateinit var item: Item
    private lateinit var type: ItemType
    companion object{
        fun newInstance(item: Item,type: ItemType):ItemDescriptionDialog{
            return ItemDescriptionDialog().apply {
                this.item = item
                this.type = type
            }

        }
    }
    private lateinit var binding:ItemInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemInfoBinding.inflate(inflater)
//        getImageFromUrl(binding.imgDesc,item.getImgLink())
//        binding.txtItemName.text = item.name
//        binding.txtPrice.text = "Giá: ${item.price} VND"
//        binding.txtDesc.text = item.desc
        binding.item = item
        binding.txtType.text = type.type
        binding.btnCancel.setOnClickListener { 
            dismiss()
        }
        binding.btnBuy.setOnClickListener { 
            buyItem(item)
            dismiss()
        }
        return binding.root
    }

    private fun buyItem(item: Item) {
        Toast.makeText(context, "Purchase completed", Toast.LENGTH_SHORT).show()

    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
}