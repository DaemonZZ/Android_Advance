package com.example.mvvmprojecttemplate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmprojecttemplate.adapter.listener.RecyclerViewItemClickListener
import com.example.mvvmprojecttemplate.databinding.ItemRvItemBinding
import com.example.mvvmprojecttemplate.model.Item

class ItemRecyclerViewAdapter(private val list:List<Item>):RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemHolder>() {
    private lateinit var listener:RecyclerViewItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.btnShow.setOnClickListener {
            listener.onButtonShowClicked(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setButtonClickListener(listener: RecyclerViewItemClickListener){
        this.listener=listener
    }

    class ItemHolder(val binding:ItemRvItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Item){
            binding.item = item
        }
    }

}