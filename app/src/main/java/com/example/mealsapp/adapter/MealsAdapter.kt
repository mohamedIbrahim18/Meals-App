package com.example.mealsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.CategoriesItem
import com.example.trymeals.databinding.CatgeoryItemBinding

class MealsAdapter : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    class ViewHolder(private val itemBinding: CatgeoryItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
            fun bind(category : CategoriesItem){
                itemBinding.apply {
                    categoryNameTv.text = category.strCategory
                    categoryDesTv.text = category.strCategoryDescription
                }
                Glide.with(itemBinding.root.context)
                    .load(category.strCategoryThumb)
                    .circleCrop()
                    .into(itemBinding.categoryIv)
            }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<CategoriesItem>(){
        override fun areItemsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean {
            return  oldItem == newItem
        }

    }
     val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = CatgeoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
    }
}