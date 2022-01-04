package com.example.onlineshop.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.model.CategoriesModel
import kotlinx.android.synthetic.main.category_item_layout.view.*

class CategoryAdapter(val items: List<CategoriesModel>) :
    RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.tvTitle.text = item.title
        holder.itemView.setOnClickListener {
            items.forEach {
                it.checked = false
            }
            item.checked = true
            notifyDataSetChanged()
        }

        if (item.checked) {
            holder.itemView.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorPrimary
                )
            )
            holder.itemView.tvTitle.setTextColor(Color.WHITE)
        } else {
            holder.itemView.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.grey
                )
            )
            holder.itemView.tvTitle.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}