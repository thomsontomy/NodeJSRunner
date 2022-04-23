package io.github.thomsontomy.nodejs

import android.content.Context
import androidx.core.content.edit
import java.io.File

class NodeJsRunner(private val context: Context) {

    fun startNodeWithJsCode(jsCode: String) {
        loadLibraries()
        startNodeWithArguments(
            arrayOf(
                "node",
                "--trace-uncaught",
                "-e",
                jsCode
            )
        )
    }

    @JvmOverloads
    fun startNodeWithJsResourceFile(
        jsResourceFile: String,
        arguments: Array<String> = emptyArray()
    ) {
        if (wasAppUpdated()) {
            copyFileToLocal(jsResourceFile)
            storeLastUpdatedTimeStamp()
        }
        startServer("${context.filesDir}/$jsResourceFile", arguments)
    }

    @JvmOverloads
    fun startNodeWithJsResourceFolderAndFile(
        nodeJsAssetDirectory: String,
        startupScriptPath: String,
        arguments: Array<String> = emptyArray()
    ) {
        if (wasAppUpdated()) {
            copyAssetDirectory(nodeJsAssetDirectory)
            storeLastUpdatedTimeStamp()
        }
        startServer("${context.filesDir}/$startupScriptPath", arguments)
    }

    private fun startServer(startupScriptPath: String, arguments: Array<String> = emptyArray()) {
        loadLibraries()
        val args = arrayOf(
            "node",
            startupScriptPath
        ) + arguments
        startNodeWithArguments(args)
    }

    private fun copyAssetDirectory(filePath: String) {
        val fileList = context.assets.list(filePath) ?: return

        if (fileList.isEmpty()) {
            copyFileToLocal(filePath)
            return
        }

        File(context.filesDir, filePath).mkdirs()
        fileList.forEach {
            copyAssetDirectory("$filePath/$it")
        }
    }

    private fun copyFileToLocal(assetFilePath: String) {
        context.assets.open(assetFilePath).use { input ->
            File(context.filesDir, assetFilePath).outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun loadLibraries() {
        System.loadLibrary("node")
        System.loadLibrary("nodejsbridge")
    }

    private fun storeLastUpdatedTimeStamp() {
        val lastUpdatedTimeStamp = context.applicationContext.packageManager.getPackageInfo(
            context.applicationContext.packageName,
            0
        ).lastUpdateTime
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit {
            putLong(TIMESTAMP_KEY, lastUpdatedTimeStamp)
        }
    }

    private fun wasAppUpdated(): Boolean {
        val lastUpdatedTimeStamp = context.applicationContext.packageManager.getPackageInfo(
            context.applicationContext.packageName,
            0
        ).lastUpdateTime
        val previousLastUpdateTimeStamp =
            context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).getLong(TIMESTAMP_KEY, 0)
        return lastUpdatedTimeStamp != previousLastUpdateTimeStamp
    }

    external fun startNodeWithArguments(arguments: Array<String>): Int

    companion object {
        private const val PREF_FILE = "io.github.thomsontomy.nodejs"
        private const val TIMESTAMP_KEY = "lastCopiedTimeStamp"
    }
}