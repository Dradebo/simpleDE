package com.example.simplede.domain.repository

import com.example.simplede.domain.model.DatasetInstance

interface DatasetInstancesRepository {
    suspend fun getDatasetInstances(): List<DatasetInstance>
    suspend fun syncDatasetInstances()
}