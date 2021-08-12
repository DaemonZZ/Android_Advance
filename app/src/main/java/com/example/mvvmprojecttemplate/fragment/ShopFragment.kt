package com.example.mvvmprojecttemplate.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmprojecttemplate.adapter.ItemRecyclerViewAdapter
import com.example.mvvmprojecttemplate.adapter.listener.RecyclerViewItemClickListener
import com.example.mvvmprojecttemplate.adapter.TypeRecyclerViewAdapter
import com.example.mvvmprojecttemplate.databinding.FragmentShopBinding
import com.example.mvvmprojecttemplate.model.Item
import com.example.mvvmprojecttemplate.model.ItemType
import com.example.mvvmprojecttemplate.network.DataPuller
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ShopFragment"

class ShopFragment : Fragment(), RecyclerViewItemClickListener {
    private val job = Job()
    private val mainScope = CoroutineScope(Dispatchers.IO + job)
    private lateinit var binding: FragmentShopBinding
    private var listType = listOf<ItemType>()
    private var listItems = listOf<Item>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater)
        getData()
        return binding.root
    }


    fun getData() {
        mainScope.launch {
            DataPuller.service.getTypes().enqueue(object : Callback<List<ItemType>> {
                override fun onResponse(
                    call: Call<List<ItemType>>,
                    response: Response<List<ItemType>>
                ) {
                    if (response.isSuccessful) {
                        val types = response.body()
                        if (types != null) {
                            listType = types
                            val adapter = TypeRecyclerViewAdapter(types)
                            adapter.setItemClickListener(this@ShopFragment)
                            binding.typeItemRv.adapter = adapter
                            val layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            binding.typeItemRv.isNestedScrollingEnabled = true
                            binding.typeItemRv.layoutManager = layoutManager
                        }
                    } else {
                        Log.e(TAG, "onResponse: No Response")
                    }
                }

                override fun onFailure(call: Call<List<ItemType>>, t: Throwable) {
                    Log.e(TAG, "---------onFailure: Get List Items------------")
                    t.printStackTrace()
                }

            })
            DataPuller.service.getItems().enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    if (response.isSuccessful) {
                        if (response.body() != null)
                            listItems = response.body()!!
                    } else {
                        Log.e(TAG, "onResponse: No Response")
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    Log.e(TAG, "---------onFailure: Get List Items------------")
                    t.printStackTrace()
                }

            })
        }

    }

    override fun onClickTypeItem(id: Int) {
        val filteredList = listItems.filter { it.typeId == id }
        val adapter = ItemRecyclerViewAdapter(filteredList)
        binding.itemRv.adapter = adapter
        adapter.setButtonClickListener(this)
        binding.itemRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


    }

    override fun onButtonShowClicked(id: Int) {
        val item = listItems.first { it.id ==id }
        val type =listType.first { item.typeId == it.id }
        Log.d(TAG, "onButtonShowClicked: ${type.id}")
        val mng = requireActivity().supportFragmentManager
        ItemDescriptionDialog.newInstance(item,type).show(mng,"desc")

    }
}