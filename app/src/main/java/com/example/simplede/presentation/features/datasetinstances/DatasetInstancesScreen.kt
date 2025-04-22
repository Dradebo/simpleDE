package com.example.simplede.presentation.features.datasetInstances

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplede.presentation.components.BaseScreen
import org.hisp.dhis.mobile.ui.designsystem.component.*
import org.hisp.dhis.mobile.ui.designsystem.component.state.*
import java.net.URLEncoder

@Composable
fun DatasetInstancesScreen(
    navController: NavController,
    datasetId: String,
    datasetName: String,
    viewModel: DatasetInstancesViewModel
) {
    val state by viewModel.instancesState.collectAsState()

    BaseScreen(
        title = datasetName,
        navController = navController,
        actions = {
            IconButton(onClick = { viewModel.syncDatasetInstances() }) {
                Icon(
                    imageVector = Icons.Default.Sync,
                    contentDescription = "Sync",
                    tint = TextColor.OnSurface
                )
            }
            IconButton(onClick = { /* TODO: Implement filter dialog */ }) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = TextColor.OnSurface
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is DatasetInstancesState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is DatasetInstancesState.Error -> {
                    Text(
                        text = (state as DatasetInstancesState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                is DatasetInstancesState.Success -> {


                    val syncItem = AdditionalInfoItem(
                        key = "",
                        value = "",
                        isConstantItem = true
                    )


                    val successState = state as DatasetInstancesState.Success
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(successState.filteredInstances) { instance ->
                            ListCard(
                                modifier = Modifier.fillMaxWidth(),
                                listCardState = rememberListCardState(
                                    title = ListCardTitleModel(
                                        text = instance.period.toString(),
                                        modifier = Modifier.padding(0.dp)
                                    ),
                                    description = ListCardDescriptionModel(
                                        text = instance.state.toString()
                                    ),
                                    additionalInfoColumnState = rememberAdditionalInfoColumnState(
                                        additionalInfoList = listOf(
                                            AdditionalInfoItem(
                                                key = "Last Updated",
                                                value = "",
                                                isConstantItem = true
                                            )
                                        ),
                                        syncProgressItem = syncItem,
                                    ),
                                    shadow = true
                                ),
                                onCardClick = {
                                    val encodedDatasetName = java.net.URLEncoder.encode(datasetName, "UTF-8")
                                    val encodedPeriod = java.net.URLEncoder.encode(instance.period.id, "UTF-8")
                                    val encodedAttributeOptionCombo = java.net.URLEncoder.encode(instance.attributeOptionCombo, "UTF-8")
                                    navController.navigate(
                                        "EditEntry/$datasetId/${instance.id}/$encodedDatasetName/$encodedPeriod/$encodedAttributeOptionCombo"
                                    )
                                }
                            )
                        }
                    }
                }
            }

            FloatingActionButton(
                onClick = {
                    val encodedDatasetName = URLEncoder.encode(datasetName, "UTF-8")
                    navController.navigate("CreateDataEntry/$datasetId/$encodedDatasetName")
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Data Entry"
                )
            }
        }
    }
}