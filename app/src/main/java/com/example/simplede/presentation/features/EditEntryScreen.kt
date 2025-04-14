package com.example.simplede.presentation.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplede.presentation.components.BaseScreen
import org.hisp.dhis.mobile.ui.designsystem.component.InputNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

data class FormScreenState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val address: String = "",
    val age: String = ""
)





@Composable
fun EditEntryScreen(navController: NavController) {
    var showQualityDialog by remember { mutableStateOf(false) }
    var showDatasetDialog by remember { mutableStateOf(false) }
    
    BaseScreen(
        title = "Data Entry",
        navController = navController
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Form Content
            Spacer(modifier = Modifier.size(Spacing.Spacing40))
            
            var name by remember { mutableStateOf(TextFieldValue("")) }
            var surname by remember { mutableStateOf(TextFieldValue("")) }
            var email by remember { mutableStateOf(TextFieldValue("")) }
            var address by remember { mutableStateOf(TextFieldValue("")) }
            var age by remember { mutableStateOf(TextFieldValue("")) }

            InputText(
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
                state =InputShellState.UNFOCUSED,
                title = "Name",
                inputTextFieldValue = name,
                onValueChanged = {
                    if (it != null) {
                        name = it
                    }
                }
            )

            InputText(
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
                state =InputShellState.UNFOCUSED,
                title = "Surname",
                inputTextFieldValue = surname,
                onValueChanged = {
                    if (it != null) {
                        surname = it
                    }
                }
            )

            InputText(
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
                state = InputShellState.UNFOCUSED,
                title = "Email",
                inputTextFieldValue = email,
                onValueChanged = {
                    if (it != null) {
                        email = it
                    }
                }
            )

            InputText(
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
                state = InputShellState.UNFOCUSED,
                title = "Address",
                inputTextFieldValue = address,
                onValueChanged = {
                    if (it != null) {
                        address = it
                    }
                }
            )

            var ageState by remember { mutableStateOf(InputShellState.UNFOCUSED) }
            InputNumber(
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
                state = ageState,
                title = "Age",
                inputTextFieldValue = age,
                onValueChanged = {
                    if (it != null) {
                        age = it
                        try {
                            ageState = getAgeInputState(it.text.toInt())
                        } catch (e: NumberFormatException) {
                            ageState = InputShellState.ERROR
                        }
                    }
                },
                supportingText = getAgeInputSupportingText(ageState)
            )

            // Floating Action Button
            FloatingActionButton(
                onClick = { showQualityDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save")
            }
        }

        // First Dialog - Quality Check
        if (showQualityDialog) {
            AlertDialog(
                onDismissRequest = { showQualityDialog = false },
                title = { Text("Saved") },
                text = { Text("Do you want to check for quality?") },
                confirmButton = {
                    TextButton(onClick = {
                        showQualityDialog = false
                        showDatasetDialog = true
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showQualityDialog = false
                        navController.navigate("datasetInstances")
                    }) {
                        Text("No")
                    }
                }
            )
        }

        // Second Dialog - Dataset Completion
        if (showDatasetDialog) {
            AlertDialog(
                onDismissRequest = { showDatasetDialog = false },
                title = { Text("Quality Check Complete") },
                text = { Text("Data quality has been checked\nDo you want to also complete the dataset?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDatasetDialog = false
                        navController.navigate("datasetInstances")
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatasetDialog = false
                        navController.navigate("datasetInstances")
                    }) {
                        Text("No")
                    }
                }
            )
        }
    }
}

fun getAgeInputSupportingText(ageState: InputShellState): List<SupportingTextData>? {
    return when(ageState) {
        InputShellState.ERROR -> listOf(SupportingTextData("Age must be between 18 and 120", SupportingTextState.ERROR))
        else -> null
    }
}

fun getAgeInputState(value: Int): InputShellState {
    return when(value) {
        in 0..17 -> InputShellState.ERROR
        in 18..120 -> InputShellState.FOCUSED
        else -> InputShellState.ERROR
    }
} 