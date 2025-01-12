package com.liang.androidagp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liang.androidagp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test(100)
        sum(1, 2)
    }

    private fun test(time: Long) {
        Thread.sleep(time)
    }

    private fun sum(i: Int, j: Int): Int {
        return i + j
    }
}