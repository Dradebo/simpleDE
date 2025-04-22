package com.example.simplede.presentation.features.datasetInstances


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplede.data.repositoryImpl.DatasetInstancesRepositoryImpl
import com.example.simplede.domain.useCases.datasetInstances.FilterDatasetInstancesUseCase
import com.example.simplede.domain.useCases.datasetInstances.GetDatasetInstancesUseCase
import com.example.simplede.domain.useCases.datasetInstances.SyncDatasetInstancesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DatasetInstancesViewModelFactory(private val datasetId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatasetInstancesViewModel::class.java)) {
            val repository = DatasetInstancesRepositoryImpl()
            @Suppress("UNCHECKED_CAST")
            return DatasetInstancesViewModel(
                datasetId = datasetId,
                getDatasetInstancesUseCase = GetDatasetInstancesUseCase(repository),
                syncDatasetInstancesUseCase = SyncDatasetInstancesUseCase(repository),
                filterDatasetInstancesUseCase = FilterDatasetInstancesUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DatasetInstancesViewModel(
    private val datasetId: String,
    private val getDatasetInstancesUseCase: GetDatasetInstancesUseCase,
    private val syncDatasetInstancesUseCase: SyncDatasetInstancesUseCase,
    private val filterDatasetInstancesUseCase: FilterDatasetInstancesUseCase
) : ViewModel() {

    private val _instancesState = MutableStateFlow<DatasetInstancesState>(DatasetInstancesState.Loading)
    val instancesState: StateFlow<DatasetInstancesState> = _instancesState.asStateFlow()

    init {
        loadDatasetInstances()
    }

    fun loadDatasetInstances() {
        viewModelScope.launch {
            _instancesState.value = DatasetInstancesState.Loading
            try {
                val result = getDatasetInstancesUseCase()
                result.fold(
                    onSuccess = { instances ->
                        _instancesState.value = DatasetInstancesState.Success(
                            datasetInstances = instances,
                            filteredInstances = instances
                        )
                    },
                    onFailure = { exception ->
                        _instancesState.value = DatasetInstancesState.Error(
                            message = exception.message ?: "Failed to load dataset instances"
                        )
                    }
                )
            } catch (e: Exception) {
                _instancesState.value = DatasetInstancesState.Error(
                    message = "Failed to load dataset instances: ${e.message}"
                )
            }
        }
    }

    fun syncDatasetInstances() {
        viewModelScope.launch {
            val currentState = _instancesState.value
            if (currentState is DatasetInstancesState.Success) {
                _instancesState.value = currentState.copy(isSyncing = true)

                try {
                    val result = syncDatasetInstancesUseCase()
                    result.fold(
                        onSuccess = {
                            // Reload instances after successful sync
                            loadDatasetInstances()
                        },
                        onFailure = { exception ->
                            _instancesState.value = DatasetInstancesState.Error(
                                message = exception.message ?: "Failed to sync dataset instances"
                            )
                        }
                    )
                } catch (e: Exception) {
                    _instancesState.value = DatasetInstancesState.Error(
                        message = "Failed to sync dataset instances: ${e.message}"
                    )
                }
            }
        }
    }

    fun filterInstances(period: String? = null, state: String? = null) {
        viewModelScope.launch {
            val currentState = _instancesState.value
            if (currentState is DatasetInstancesState.Success) {
                try {
                    val result = filterDatasetInstancesUseCase(period, state)
                    result.fold(
                        onSuccess = { filteredInstances ->
                            _instancesState.value = currentState.copy(
                                filteredInstances = filteredInstances,
                                selectedPeriod = period,
                                selectedState = state
                            )
                        },
                        onFailure = { exception ->
                            _instancesState.value = DatasetInstancesState.Error(
                                message = exception.message ?: "Failed to filter dataset instances"
                            )
                        }
                    )
                } catch (e: Exception) {
                    _instancesState.value = DatasetInstancesState.Error(
                        message = "Failed to filter dataset instances: ${e.message}"
                    )
                }
            }
        }
    }

    fun clearFilters() {
        val currentState = _instancesState.value
        if (currentState is DatasetInstancesState.Success) {
            _instancesState.value = currentState.copy(
                filteredInstances = currentState.datasetInstances,
                selectedPeriod = null,
                selectedState = null
            )
        }
    }
}