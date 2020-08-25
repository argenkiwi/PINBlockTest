package com.example.pintest

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PINBlockUnitTest {

    @Test
    fun toIPB() {
        // Arrange
        val pin = "123456"

        // Act
        val ipb = com.example.pintest.toIPB(pin)

        // Assert
        assertTrue(ipb.startsWith("36123456"))
    }

    @Test
    fun toPANBlock() {
        // Arrange
        val pan = "111222333444555";

        // Act
        val panBlock = toPANBlock(pan)

        // Assert
        assertEquals("0000222333444555", panBlock)
    }

    @Test
    fun toPINBlock() {
        // Arrange
        val pin = "123456";
        val pan = "111222333444555";

        // Act
        val pinBlock = runBlocking {
            toPINBlock(pin, pan)
        }

        // Assert
        assertTrue(pinBlock.startsWith("36121675"))
    }
}
