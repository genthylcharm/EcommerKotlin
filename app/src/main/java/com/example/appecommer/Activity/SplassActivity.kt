package com.example.appecommer.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appecommer.R
import com.example.appecommer.databinding.ActivitySplassBinding

class SplassActivity : BaseAct() {
    private lateinit var binding: ActivitySplassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
        val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}