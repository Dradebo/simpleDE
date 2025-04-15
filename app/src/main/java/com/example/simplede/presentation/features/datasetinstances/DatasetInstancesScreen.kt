package com.example.simplede.presentation.features.datasetinstances

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplede.presentation.components.BaseScreen
import org.hisp.dhis.mobile.ui.designsystem.component.*
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun DatasetInstancesScreen(
    navController: NavController,
    datasetId: String,
    datasetName: String
) {
    BaseScreen(
        title = datasetName,
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
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val titleModel = ListCardTitleModel(
                text = "Latest Entry",
                modifier = Modifier.padding(0.dp)
            )
            
            val descriptionModel = ListCardDescriptionModel(
                text = "View and edit your latest dataset entry"
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
                onCardClick = { navController.navigate("EditEntry") }
            )

            FloatingActionButton(
                onClick = { navController.navigate("CreateNewEntry") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create New Entry"
                )
            }
        }
    }
}