package ru.mts.ngenie

import com.beust.jcommander.JCommander
import org.junit.Assert.*
import org.junit.Test

class ArgsTest {

    val args = Args()

    @Test
    fun parseHostAndTopic() {
        val myHostValue = "iot.mts.ru"
        val myTopic = "temperature"

        JCommander.newBuilder()
                .programName("Mqtt device(sensor) emulator")
                .addObject(args)
                .build()
                .parse("-h", myHostValue, "--topic", myTopic)
        assertEquals(myHostValue, args.host)
        assertEquals(myTopic, args.topic)
    }

    @Test
    fun parseHostAndPortAndTopic() {
        val myHostValue = "iot.mts.ru"
        val port = "9090"
        val myTopic = "temperature"
        val passArgs = arrayOf("-h", myHostValue, "-p", port, "--topic", myTopic)
        JCommander.newBuilder()
                .programName("Mqtt device(sensor) emulator")
                .addObject(args)
                .build()
                .parse(*passArgs)

        assertEquals(myHostValue, args.host)
        assertEquals(port.toInt(), args.port)
        assertEquals(myTopic, args.topic)
    }
}