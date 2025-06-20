package com.example.testeableapp

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.swipeLeft
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

        composeTestRule.onNodeWithTag("billInput").performTextInput("100")
        composeTestRule.onNodeWithTag("roundUpCheckbox").performClick()
        composeTestRule.onNodeWithTag("tipResult").assertTextEquals("Propina: $15.00")
    }

    @Test
    fun cambiarSliderDePropina_yVerificarCalculo() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule.onNodeWithTag("billInput").performTextInput("100")

        composeTestRule.onNodeWithTag("tipSlider").performSemanticsAction(SemanticsActions.SetProgress) {
            it(25.0f)
        }

        composeTestRule.onNodeWithTag("tipResult").assertTextEquals("Propina: $25.00")
    }

    @Test
    fun validarElementosUI_visibles() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule.onNodeWithTag("billInput").assertIsDisplayed()
        composeTestRule.onNodeWithTag("tipSlider").assertIsDisplayed()
        composeTestRule.onNodeWithTag("roundUpCheckbox").assertIsDisplayed()
        composeTestRule.onNodeWithTag("plusButton").assertIsDisplayed()
        composeTestRule.onNodeWithText("Número de personas: 1").assertIsDisplayed()
    }

    @Test
    fun verificarCamposDiferentesDeO(){
        composeTestRule.setContent {
            TipCalculatorScreen()
        }
        composeTestRule.onNodeWithText("Monto de la cuenta").performTextClearance()
        composeTestRule.onNodeWithTag("tipSlider").performTouchInput { swipeLeft() }
        composeTestRule.onNodeWithText("-").performClick()

        composeTestRule.onNodeWithTag("tipResult").assertTextEquals("Propina: $0.00")
        composeTestRule.onNodeWithTag("totalPerPersonResult").assertTextEquals("Total por persona: $0.00")
        }

    @Test
    fun validarLimiteInferior_CantidadDePersonas_Test(){

        composeTestRule.setContent {
            TipCalculatorScreen()
        }
        repeat(3) {
            composeTestRule.onNodeWithText("-").performClick()
        }
        composeTestRule.onNodeWithText("1").assertExists()
        }
}