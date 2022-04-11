package com.thomson.nativapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.thomson.nativapp.databinding.ActivityMainBinding
import io.github.thomsontomy.nodejs.NodeJsRunner
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val nodeJsRunner: NodeJsRunner = NodeJsRunner(this)
    private var nodeThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartWithCode.setOnClickListener { startServerWithCode() }
        binding.buttonStartWithResoureFile.setOnClickListener { startServerWithResourceFile() }
        binding.buttonStartWithResourceFolder.setOnClickListener { startServerWithResourceFolder() }
    }

    private fun startServerWithCode() {
        nodeThread?.interrupt()
        Log.e("TESST", "waiting for $nodeThread to finish")
        nodeThread?.join()
        val code = binding.editTextJSCode.text.toString()
        nodeThread = Thread {
            nodeJsRunner.startNodeWithJsCode(code)
        }
        nodeThread?.setUncaughtExceptionHandler { thread, throwable ->
            Log.e("TESST", "Hmm Errr!! $thread", throwable)
        }
        Log.e("TESST", "Starting $nodeThread")
        nodeThread?.start()
    }

    private fun startServerWithResourceFile() {
        Thread {
            nodeJsRunner.startNodeWithJsResourceFile("start_server.js", arrayOf("mode=mobileApp"))
        }.start()
    }

    private fun startServerWithResourceFolder() {
        Thread {
            nodeJsRunner.startNodeWithJsResourceFolderAndFile(
                "mockresponses",
                "mockresponses/server/bin/www",
                arrayOf("path=${File(filesDir, "mockresponses/").absolutePath}")
            )
        }.start()
    }
}