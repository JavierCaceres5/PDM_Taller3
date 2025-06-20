package com.example.testeableapp

import org.junit.Test
import kotlin.math.ceil
import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorTest {
    @Test
    fun `calcula propina con 37% y redondeo`() {
        val tip = calculateTip(amount = 150.0, tipPercent = 37, roundUp = true)
        val expected = ceil(150 * 0.37)
        println("TIP CALCULATED: $tip, EXPECTED: $expected")
        assertEquals(expected, tip, 0.001)
    }

    @Test
    fun `cantidad negativa debe retornar 0`() {
        val tip = calculateTip(amount = -50.0, tipPercent = 15, roundUp = false)
        println("TIP CALCULATED: $tip (Expected: 0.0)")
        assertEquals(0.0, tip,0.001)
    }

    @Test
    fun `calcula total a pagar por persona`() {
        val bill = 120.0
        val tip = calculateTip(bill, 10, false)
        val totalPerPerson = (bill + tip) / 4
        println("TOTAL PER PERSON: $totalPerPerson (Expected: 33.0)")
        assertEquals(33.0, totalPerPerson, 0.001)
    }

    @Test
    fun CalcularPropinaSinRedondeo(){
        val tip = calculateTip(amount = 125.0, tipPercent = 15, roundUp = false)
        val expected = 125.0 * 15 / 100
        assertEquals(tip, expected, 0.001)
    }

    @Test
    fun CalcularPropinaConMontoCero(){

        val amount = 0.0
        val tipPercent = 20
        val roundUp = false

        val tip = calculateTip(amount, tipPercent, roundUp)

        val expectedTip = 0.0
        assertEquals(expectedTip, tip, 0.001)
        }
}