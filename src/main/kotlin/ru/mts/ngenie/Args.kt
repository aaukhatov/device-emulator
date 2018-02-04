package ru.mts.ngenie

import com.beust.jcommander.Parameter

class Args {

    @Parameter(names = arrayOf("-h", "--host"), description = "Mqtt broker host", required = true)
    var host: String = "localhost"

    @Parameter(names = arrayOf("-p", "--port"), description = "Mqtt broker port")
    var port: Int = 1883

    @Parameter(names = arrayOf("--topic"), description = "Topic name", required = true)
    lateinit var topic: String

    @Parameter(names = arrayOf("--timeout"), description = "Data send frequency (ms)")
    var timeout: Long = 1_000

    @Parameter(names = arrayOf("--help"), description = "Information how to use app?", help = true)
    var help: Boolean = false
}