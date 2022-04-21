package com.thomson.sample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thomson.sample2.databinding.ActivityMainBinding
import io.github.thomsontomy.nodejs.NodeJsRunner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonStartWithResoureFile.setOnClickListener { startServerWithResourceFile() }
    }

    private fun startServerWithResourceFile() {
        Thread {
            NodeJsRunner(this).startNodeWithJsResourceFile(
                "start_server.js",
                arrayOf("mode=mobileApp")
            )
        }.start()
    }
}