package com.example.appecommer.Activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appecommer.Adapter.CartAdapter
import com.example.appecommer.DataBase.ChangeNumberItemsListener
import com.example.appecommer.DataBase.ManagmentCart
import com.example.appecommer.R
import com.example.appecommer.databinding.ActivityCartBinding


class CartActivity : BaseAct() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculatorCart()

    }

    private fun initCartList() {
        binding.viewcart.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewcart.adapter =
            CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculatorCart()
                }
            })
        with(binding) {
            emptyTxt.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView3.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setVariable() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

            method1.setOnClickListener {
                method1.setBackgroundResource(R.drawable.green_bg_selected)
                methodIc1.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity, R.color.green))
                methodTitle1Txt.setTextColor(getResources().getColor(R.color.green))
                methodSubtitle.setTextColor(getResources().getColor(R.color.green))

                // Reset màu sắc của method2 nếu method1 được chọn
                method2.setBackgroundResource(R.drawable.grey_bg_selected)
                methodIc2.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity, R.color.grey))
                methodTitle2Txt.setTextColor(getResources().getColor(R.color.grey))
                methodSubTitle2.setTextColor(getResources().getColor(R.color.grey))
            }

            method2.setOnClickListener {
                method2.setBackgroundResource(R.drawable.green_bg_selected)
                methodIc2.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity, R.color.green))
                methodTitle2Txt.setTextColor(getResources().getColor(R.color.green))
                methodSubTitle2.setTextColor(getResources().getColor(R.color.green))

                // Reset màu sắc của method1 nếu method2 được chọn
                method1.setBackgroundResource(R.drawable.grey_bg_selected)
                methodIc1.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this@CartActivity, R.color.grey))
                methodTitle1Txt.setTextColor(getResources().getColor(R.color.grey))
                methodSubtitle.setTextColor(getResources().getColor(R.color.grey))
            }
        }
}

    private fun calculatorCart() {
        val percentax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100.0
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100.0

        with(binding) {
            totalFeeTxt.text = "$${String.format("%.2f", itemTotal)}"
            taxTxt.text = "$${String.format("%.2f", tax)}"
            deliveryTxt.text = "$${String.format("%.2f", delivery)}"
            toTalTxt.text = "$${String.format("%.2f", total)}"
        }
    }
    fun Double.roundTo(digits: Int) = "%.${digits}f".format(this).toDouble()
}