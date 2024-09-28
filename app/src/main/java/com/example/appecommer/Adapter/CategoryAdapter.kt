package com.example.appecommer.Adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appecommer.Activity.ListItemsActivity
import com.example.appecommer.Model.CategoryModel
import com.example.appecommer.R
import com.example.appecommer.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title

        // Tải ảnh bằng Glide
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        // Kiểm tra xem mục này có được chọn hay không
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.green_btn)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.white)
                )
            )
            holder.binding.titleTxt.visibility = View.VISIBLE
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white)
            )
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.primary)
                )
            )
            holder.binding.titleTxt.visibility = View.GONE
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.primary)
            )
        }

        // Xử lý sự kiện khi người dùng chọn item
        holder.binding.root.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION && currentPosition != selectedPosition) {
                // Cập nhật vị trí được chọn và vị trí cuối cùng đã chọn
                lastSelectedPosition = selectedPosition
                selectedPosition = currentPosition

                // Thông báo để cập nhật lại giao diện cho các mục cũ và mới
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)

                // Thêm delay trước khi chuyển sang activity khác
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(holder.itemView.context, ListItemsActivity::class.java).apply {
                        putExtra("id", item.id.toString())
                        putExtra("title", item.title)
                    }
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }, 1000)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
