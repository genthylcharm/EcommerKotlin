package com.example.appecommer.Adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appecommer.R
import com.example.appecommer.databinding.ViewhodelModelBinding


class SelectModelAdapter(val items: MutableList<String>) :
    RecyclerView.Adapter<SelectModelAdapter.Viewhodel>() {
    private var selectedPosition = -1
    private var lastselectedPosition = -1
    private lateinit var context: Context

    inner class Viewhodel(val binding: ViewhodelModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectModelAdapter.Viewhodel {
        context = parent.context
        val binding = ViewhodelModelBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewhodel(binding)
    }

    override fun onBindViewHolder(holder: SelectModelAdapter.Viewhodel, position: Int) {
        holder.binding.modeTxt.text = items[position]
        holder.binding.root.setOnClickListener {
            lastselectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastselectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == position) {
            holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_selected)
            holder.binding.modeTxt.setTextColor(context.resources.getColor(R.color.green))
        } else {
            holder.binding.modelLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.modeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size
}