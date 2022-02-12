package com.github.wlmxenl.exercise.custom

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.junit.Test
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Paths

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SampleDataTest {

    @Test
    fun printCountryList() {
        File(Paths.get("").toFile().absolutePath, "sampledata${File.separator}country.json")
            .inputStream()
            .readBytes()
            .toString(Charset.defaultCharset())
            .let {
                JsonParser.parseString(it).asJsonArray
            }
            .map {
                (it as JsonObject)["cnname"].asString
            }
            .also {
                // println(it.toString())
                // 输出 kotlin array 代码
                val codeTextBuilder = StringBuilder("arrayOf(")
                it.forEachIndexed { index, countryName ->
                    if (index > 0) {
                        codeTextBuilder.append(", ")
                    }
                    codeTextBuilder.append("\"$countryName\"")
                }
                codeTextBuilder.append(")")
                println(codeTextBuilder.toString())
            }
    }
}