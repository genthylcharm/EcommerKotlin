package com.example.appecommer.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.appecommer.Adapter.PicAdapter
import com.example.appecommer.Adapter.SelectModelAdapter
import com.example.appecommer.DataBase.ManagmentCart
import com.example.appecommer.Model.ItemsModel
import com.example.appecommer.databinding.ActivityDeitailBinding


class DeitailActivity : BaseAct() {
    private lateinit var binding: ActivityDeitailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentcart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeitailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentcart = ManagmentCart(this)
        getBundle()
        initList()
    }

    private fun initList() {
        val modeList = ArrayList<String>()
        for (models in item.model) {
            modeList.add(models)
        }
        binding.modelList.adapter = SelectModelAdapter(modeList)
        binding.modelList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val picList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            picList.add(imageUrl)
        }
        Glide.with(this)
            .load(picList[0])
            .into(binding.pic)

        binding.picList.adapter = PicAdapter(picList) { selectedImageUrl ->
            Glide.with(this)
                .load(selectedImageUrl)
                .into(binding.pic)
        }
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managmentcart.insertItems(item)
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@DeitailActivity, CartActivity::class.java))
        }
    }
}