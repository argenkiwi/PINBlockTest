package com.example.pintest

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ToPINBlockUnitTest {

    @Test
    fun success() {
        // Arrange
        val pin = "123456";
        val pan = "111222333444555";

        // Act
        val pinBlock = toPINBlock(pin, pan)

        // Assert
        assertEquals("361216759CFA8889", pinBlock)
    }
}
