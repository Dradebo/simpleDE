package com.example.simplede.domain.model

data class DatasetInstance(
    val id: String,
    val datasetId: String,
    val period: Period,
    val organisationUnit: OrganisationUnit,
    val attributeOptionCombo: String,
    val state: DatasetInstanceState,
    //val lastUpdated: Date,
    //val completedDate: Date?,
    //val completedBy: String?,
    //val syncState: SyncState = SyncState.SYNCED
)

enum class DatasetInstanceState {
    OPEN,
    COMPLETE,
    APPROVED,
    LOCKED
}

data class Period(
    val id: String,
    //val startDate: Date,
    //val endDate: Date,
    //val periodType: PeriodType
)

data class OrganisationUnit(
    val id: String,
    val name: String,
    val code: String?,
    val level: Int,
    val path: String
)
