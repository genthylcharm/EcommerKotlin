package com.example.appecommer.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appecommer.Adapter.ListItemAdapter
import com.example.appecommer.ViewModel.MainViewModel
import com.example.appecommer.databinding.ActivityListItemsBinding


class ListItemsActivity : BaseAct() {
    private lateinit var binding: ActivityListItemsBinding
    private val viewMolder = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply({
            binding.backBtn.setOnClickListener {
                finish() }
            progressBarList.visibility = View.VISIBLE
            viewMolder.recommended.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager = GridLayoutManager(this@ListItemsActivity, 2)
                viewList.adapter = ListItemAdapter(it)
                progressBarList.visibility = View.GONE
            })
            viewMolder.loadFiltered(id)
        })
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!
        binding.categoryTxt.text = title

    }
}