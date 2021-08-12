package com.example.mvvmprojecttemplate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmprojecttemplate.adapter.listener.RecyclerViewItemClickListener
import com.example.mvvmprojecttemplate.databinding.TypeRvItemBinding
import com.example.mvvmprojecttemplate.model.ItemType

class TypeRecyclerViewAdapter(private val list: List<ItemType>):RecyclerView.Adapter<TypeRecyclerViewAdapter.TypeHolder>() {

    private lateinit var listener: RecyclerViewItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        val binding = TypeRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TypeHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        holder.bind(list[position])
        holder.view.setOnClickListener {
            listener.onClickTypeItem(list[position].id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItemClickListener(listener: RecyclerViewItemClickListener){
        this.listener=listener
    }

    class TypeHolder(private val binding:TypeRvItemBinding):RecyclerView.ViewHolder(binding.root){
        val view = binding.root
        fun bind(type:ItemType){
            binding.type=type
        }

    }
}

