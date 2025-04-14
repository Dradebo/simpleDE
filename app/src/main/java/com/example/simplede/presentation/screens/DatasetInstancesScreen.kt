package com.example.simplede.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplede.presentation.components.BaseScreen
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState

@Composable
fun DatasetInstancesScreen(navController: NavController) {
    BaseScreen(
        title = "Dataset Instances",
        navController = navController
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