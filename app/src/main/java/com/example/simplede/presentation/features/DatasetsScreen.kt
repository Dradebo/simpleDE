package com.example.simplede.presentation.features

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.simplede.presentation.components.BaseScreen
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.*
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatasetsScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { /* TODO: Navigate to settings */ }
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("About") },
                    selected = false,
                    onClick = { /* TODO: Navigate to about */ }
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Logout, contentDescription = null) },
                    label = { Text("Logout") },
                    selected = false,
                    onClick = { /* TODO: Implement logout */ }
                )

                Spacer(modifier = Modifier.weight(1f))

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Delete, contentDescription = null) },
                    label = { Text("Delete Account") },
                    selected = false,
                    onClick = { /* TODO: Implement account deletion */ },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedIconColor = MaterialTheme.colorScheme.error,
                        unselectedTextColor = MaterialTheme.colorScheme.error
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        gesturesEnabled = true
    ) {
        BaseScreen(
            title = "Home",
            navController = navController,
            actions = {
                IconButton(onClick = { /* TODO: Implement sync */ }) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = "Sync",
                        tint = TextColor.OnSurface
                    )
                }
                IconButton(onClick = { /* TODO: Implement filter */ }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filter",
                        tint = TextColor.OnSurface
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = TextColor.OnSurface
                    )
                }
            }
        ) {
            val titleModel = ListCardTitleModel(
                text = "Dataset Instances",
                modifier = Modifier.padding(0.dp)
            )

            val descriptionModel = ListCardDescriptionModel(
                text = "View and manage dataset instances"
            )

            val syncItem = AdditionalInfoItem(
                key = "",
                value = "",
                isConstantItem = true
            )

            val additionalInfo = rememberAdditionalInfoColumnState(
                additionalInfoList = emptyList(),
                syncProgressItem = syncItem
            )

            ListCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                listCardState = rememberListCardState(
                    title = titleModel,
                    description = descriptionModel,
                    additionalInfoColumnState = additionalInfo,
                    shadow = true
                ),
                onCardClick = { navController.navigate("DatasetInstances") }
            )
        }
    }

}