package ru.mts.ngenie

import com.beust.jcommander.JCommander
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

fun main(args: Array<String>) {
    val programArgs = Args()
    JCommander.newBuilder()
            .programName("DeviceEmulator")
            .addObject(programArgs)
            .build()
            .parse(*args)

    val brokerAddress = "tcp://${programArgs.host}:${programArgs.port}"
    val clientId = generateClientId()
    val mqttClient = connect(brokerAddress, clientId)

    while (true) {
        val message = makeMessage()
        mqttClient.publish(programArgs.topic, message)
        println("Message ${String(message.payload, Charsets.UTF_8)} published")
        Thread.sleep(programArgs.timeout)
    }
}

private fun connect(brokerAddress: String, clientId: String): MqttClient {
    val mqttClient = MqttClient(brokerAddress, clientId, MemoryPersistence())
    val conOpt = MqttConnectOptions()
    conOpt.isCleanSession = true
    mqttClient.connect(conOpt)
    return mqttClient
}

private fun makeMessage(): MqttMessage {
    val payload = generatePayload()
    val message = MqttMessage(payload.toByteArray(Charsets.UTF_8))
    message.qos = 2
    return message
}

// random client id
fun generateClientId(): String {
    val prefix = "mts:ngenie"
    val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var clientId = ""
    for (i in 0..3) {
        clientId += chars[Math.floor(Math.random() * chars.length).toInt()]
    }
    return "$prefix:$clientId"
}

// random temperature sensor value
fun generatePayload(): String = "${Math.floor(Math.random() * (25 - 17) + 20)}"
