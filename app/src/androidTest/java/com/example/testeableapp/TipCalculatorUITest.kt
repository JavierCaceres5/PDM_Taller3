package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.performClick

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TipCalculatorUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun redondearPropina_cambiaResultado() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule.onNodeWithText("Monto de la cuenta").performTextInput("100")
        composeTestRule.onNodeWithText("Redondear propina").performClick()

        composeTestRule.onNodeWithText("Propina: $15.00").assertExists()
    }

    @Test
    fun cambiarSliderDePropina_yVerificarCalculo() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule.onNodeWithText("Monto de la cuenta").performTextInput("100")

        composeTestRule.onNodeWithText("Porcentaje de propina: 15%").performTouchInput {
            swipeRight(startX = 0f, endX = 0.5f)
        }

    }
}