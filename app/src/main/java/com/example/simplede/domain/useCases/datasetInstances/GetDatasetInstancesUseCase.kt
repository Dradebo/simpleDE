package com.example.simplede.domain.useCases.datasetInstances


import com.example.simplede.domain.model.DatasetInstance
import com.example.simplede.domain.repository.DatasetInstancesRepository

class GetDatasetInstancesUseCase(
    private val repository: DatasetInstancesRepository
) {
    suspend operator fun invoke(): Result<List<DatasetInstance>> {
        return try {
            val instances = repository.getDatasetInstances()
            Result.success(instances)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}