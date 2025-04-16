package com.example.simplede.presentation.features.datasetinstances

import com.example.simplede.domain.model.DatasetInstance

sealed class DatasetInstancesState {
    data object Loading : DatasetInstancesState()
    data class Error(val message: String) : DatasetInstancesState()
    data class Success(
        val datasetInstances: List<DatasetInstance>,
        val filteredInstances: List<DatasetInstance> = datasetInstances,
        val selectedPeriod: String? = null,
        val selectedState: String? = null,
        val isSyncing: Boolean = false
    ) : DatasetInstancesState()
}