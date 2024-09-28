package com.example.appecommer.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appecommer.R
import com.example.appecommer.databinding.ViewhodelPicBinding

class PicAdapter(val items: MutableList<String>, private val onImageSelected: (String) -> Unit) :
    RecyclerView.Adapter<PicAdapter.Viewhodel>() {

    private var selectedPosition = -1
    private var lastselectedPosition = -1

    inner class Viewhodel(val binding: ViewhodelPicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicAdapter.Viewhodel {
        val binding =
            ViewhodelPicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewhodel(binding)
    }

    override fun onBindViewHolder(holder: PicAdapter.Viewhodel, position: Int) {
        val item = items[position]
        holder.binding.pic.loadImage(item)

        holder.binding.root.setOnClickListener {
            lastselectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastselectedPosition)
            notifyItemChanged(selectedPosition)

            onImageSelected(item)
        }
        if (selectedPosition == position){
            holder.binding.picLayout.setBackgroundResource(R.drawable.green_bg_selected)
        }else{
            holder.binding.picLayout.setBackgroundResource(R.drawable.green_btn)
        }
    }

    override fun getItemCount(): Int = items.size

    fun ImageView.loadImage(url: String) {
        Glide.with(this.context)
            .load(url)
            .into(this)


    }
}