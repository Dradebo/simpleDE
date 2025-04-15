package com.example.simplede.data.repository

import com.example.simplede.domain.model.Dataset
import com.example.simplede.domain.repository.DatasetRepository
import kotlinx.coroutines.delay

//class DummyDatasetRepository : DatasetRepository {
//    override suspend fun getDatasets(): Result<List<Dataset>> {
//        delay(1000) // Simulate network delay
//        return try {
//            // Dummy data
//            val datasets = listOf(
//                Dataset(
//                    id = "1",
//                    name = "COVID-19 Surveillance",
//                    description = "Weekly COVID-19 surveillance data"
//                ),
//                Dataset(
//                    id = "2",
//                    name = "Malaria Cases",
//                    description = "Monthly malaria cases reporting"
//                ),
//                Dataset(
//                    id = "3",
//                    name = "Immunization Coverage",
//                    description = "Quarterly immunization coverage data"
//                )
//            )
//            Result.success(datasets)
//        } catch (e: Exception) {
//            Result.failure(Exception("Failed to fetch datasets: ${e.message}"))
//        }
//    }
//
//    override suspend fun searchDatasets(query: String): Result<List<Dataset>> {
//        delay(500) // Simulate network delay
//        return try {
//            val allDatasets = getDatasets().getOrThrow()
//            val filteredDatasets = allDatasets.filter { dataset ->
//                dataset.name.contains(query, ignoreCase = true) ||
//                dataset.description.contains(query, ignoreCase = true)
//            }
//            Result.success(filteredDatasets)
//        } catch (e: Exception) {
//            Result.failure(Exception("Failed to search datasets: ${e.message}"))
//        }
//    }
//}