package com.example.simplede.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.simplede.presentation.components.BaseScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState

@Composable
fun DatasetsScreen(navController: NavController) {
    BaseScreen(
        title = "Home",
        navController = navController
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