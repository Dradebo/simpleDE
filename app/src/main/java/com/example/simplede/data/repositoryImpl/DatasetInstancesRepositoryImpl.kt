package com.example.simplede.data.repositoryImpl


import com.example.simplede.domain.model.*
import com.example.simplede.domain.repository.DatasetInstancesRepository
import kotlinx.coroutines.delay

class DatasetInstancesRepositoryImpl : DatasetInstancesRepository {
    private val dummyOrganisationUnits = listOf(
        OrganisationUnit(
            id = "ou1",
            name = "Central Primary School",
            code = "CPS",
            level = 4,
            path = "/country/region/district/ou1"
        ),
        OrganisationUnit(
            id = "ou2",
            name = "Eastern Secondary School",
            code = "ESS",
            level = 4,
            path = "/country/region/district/ou2"
        )
    )

    private val dummyDatasetInstances = listOf(
        DatasetInstance(
            id = "di1",
            datasetId = "1",
            period = Period(id = "202301"),
            organisationUnit = dummyOrganisationUnits[0],
            attributeOptionCombo = "Grade 1",
            state = DatasetInstanceState.OPEN
        ),
        DatasetInstance(
            id = "di2",
            datasetId = "1",
            period = Period(id = "202302"),
            organisationUnit = dummyOrganisationUnits[0],
            attributeOptionCombo = "Grade 2",
            state = DatasetInstanceState.COMPLETE
        ),
        DatasetInstance(
            id = "di3",
            datasetId = "2",
            period = Period(id = "202301"),
            organisationUnit = dummyOrganisationUnits[1],
            attributeOptionCombo = "Grade 3",
            state = DatasetInstanceState.APPROVED
        ),
        DatasetInstance(
            id = "di4",
            datasetId = "1",
            period = Period(id = "202303"),
            organisationUnit = dummyOrganisationUnits[0],
            attributeOptionCombo = "Grade 1",
            state = DatasetInstanceState.LOCKED
        ),
        DatasetInstance(
            id = "di5",
            datasetId = "2",
            period = Period(id = "202302"),
            organisationUnit = dummyOrganisationUnits[1],
            attributeOptionCombo = "Grade 2",
            state = DatasetInstanceState.OPEN
        )
    )

    override suspend fun getDatasetInstances(): List<DatasetInstance> {
        delay(500) // Simulate network delay
        return dummyDatasetInstances
    }

    override suspend fun syncDatasetInstances() {
        delay(1000) // Simulate network delay
    }
}