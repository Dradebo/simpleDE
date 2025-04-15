package com.example.simplede

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.simplede.presentation.features.CreateNewEntryScreen
import com.example.simplede.presentation.features.DatasetInstancesScreen
import com.example.simplede.presentation.features.datasets.DatasetsScreen
import com.example.simplede.presentation.features.EditEntryScreen
import com.example.simplede.presentation.features.login.LoginScreen
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DHIS2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Login") {
                        composable("Login") { LoginScreen(navController) }
                        composable("Datasets") { DatasetsScreen(navController) }
                        composable(
                            route = "DatasetInstances/{datasetId}/{datasetName}",
                            arguments = listOf(
                                navArgument("datasetId") { type = NavType.StringType },
                                navArgument("datasetName") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val datasetId = backStackEntry.arguments?.getString("datasetId") ?: ""
                            val datasetName = backStackEntry.arguments?.getString("datasetName") ?: ""
                            DatasetInstancesScreen(
                                navController = navController,
                                datasetId = datasetId,
                                datasetName = datasetName
                            )
                        }
                        composable("CreateNewEntry") { CreateNewEntryScreen(navController) }
                        composable("EditEntry") { EditEntryScreen(navController) }
                    }
                }
            }
        }
    }
}