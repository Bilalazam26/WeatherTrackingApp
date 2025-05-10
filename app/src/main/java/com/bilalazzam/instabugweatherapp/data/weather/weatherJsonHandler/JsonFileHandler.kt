package com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler

import java.io.File

class JsonFileHandler(private val file: File) {
    init {
        // Ensure parent directory exists
        file.parentFile?.let {
            if (!it.exists()) {
                it.mkdirs()
            }
        }

        // Create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    fun readJson(): String {
        if (!file.exists())
            throw Exception("File not found!")
        return file.readText()
    }

    fun writeJson(json: String) {
        if (file.exists())
            file.writeText(json)
    }
}