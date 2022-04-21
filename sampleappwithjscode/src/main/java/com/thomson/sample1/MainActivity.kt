package com.thomson.sample1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thomson.sample1.databinding.ActivityMainBinding
import io.github.thomsontomy.nodejs.NodeJsRunner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener { startServer() }
    }

    private fun startServer() {
        val jsCode = binding.textViewJsCode.text.toString()
        Toast.makeText(this, jsCode, Toast.LENGTH_SHORT).show()
        Thread {
            NodeJsRunner(this).startNodeWithJsCode(jsCode)
        }.start()
    }
}