package com.thomson.sample3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thomson.sample3.databinding.ActivityMainBinding
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
        Thread {
            NodeJsRunner(this).startNodeWithJsResourceFolderAndFile(
                nodeJsAssetDirectory = "node_server",
                startupScriptPath = "node_server/bin/www"
            )
        }.start()
    }
}