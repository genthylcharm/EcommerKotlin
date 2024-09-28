package com.example.appecommer.Activity

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.appecommer.Adapter.CategoryAdapter
import com.example.appecommer.Adapter.RecommendedAdapter
import com.example.appecommer.Adapter.SliderAdapter
import com.example.appecommer.Model.SliderModel
import com.example.appecommer.R
import com.example.appecommer.ViewModel.MainViewModel
import com.example.appecommer.databinding.ActivityMainBinding

class MainActivity : BaseAct() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initRecommeded()
        initBottomMenu()

    }

    private fun initBottomMenu() {
       binding.cartBtn.setOnClickListener {
           startActivity(Intent(this@MainActivity,CartActivity::class.java))
       }
    }

    private fun initRecommeded() {
        binding.progressPopu.visibility = View.VISIBLE
            viewModel.recommended.observe(this, Observer {
                binding.viewPopular.layoutManager = GridLayoutManager(this@MainActivity, 2)
                binding.viewPopular.adapter = RecommendedAdapter(it)
                binding.progressPopu.visibility = View.GONE

            })
            viewModel.loadRecommended()
    }

    private fun initCategory() {
        binding.progressoff.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer {
            binding.viewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressoff.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    private fun banner(image: List<SliderModel>) {
        binding.view1.adapter = SliderAdapter(image, binding.view1)
        binding.view1.clipToPadding = false
        binding.view1.clipChildren = false
        binding.view1.offscreenPageLimit = 3
        binding.view1.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Tạo một CompositePageTransformer để kết hợp nhiều transformer
        val compositePageTransformer = CompositePageTransformer().apply {
            // Thêm transformer để tạo khoảng cách giữa các trang
            addTransformer(MarginPageTransformer(40))

        }
        binding.view1.setPageTransformer(compositePageTransformer)
        if (image.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.view1)
        }
    }

    private fun initBanner() {
        binding.progressBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer {
            banner(it)
            binding.progressBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }
}