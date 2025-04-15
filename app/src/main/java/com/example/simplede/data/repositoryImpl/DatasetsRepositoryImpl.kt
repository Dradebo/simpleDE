package com.example.simplede.data.repositoryImpl

import com.example.simplede.domain.model.*
import com.example.simplede.domain.repository.DatasetRepository
import kotlinx.coroutines.delay

class DatasetsRepositoryImpl : DatasetRepository {
    private val dummyCategories = listOf(
        Category(
            id = "cat1",
            name = "Grade",
            options = listOf(
                CategoryOption(id = "opt1", name = "Grade 1", code = "G1"),
                CategoryOption(id = "opt2", name = "Grade 2", code = "G2"),
                CategoryOption(id = "opt3", name = "Grade 3", code = "G3")
            )
        ),
        Category(
            id = "cat2",
            name = "Gender",
            options = listOf(
                CategoryOption(id = "opt4", name = "Male", code = "M"),
                CategoryOption(id = "opt5", name = "Female", code = "F")
            )
        )
    )

    private val dummyCategoryCombo = CategoryCombo(
        id = "cc1",
        name = "Grade x Gender",
        categories = dummyCategories
    )

    private val dummyDataElements = listOf(
        DataElement(
            id = "de1",
            name = "Number of Students",
            valueType = ValueType.INTEGER_ZERO_OR_POSITIVE,
            categoryCombo = dummyCategoryCombo
        ),
        DataElement(
            id = "de2",
            name = "Attendance Rate",
            valueType = ValueType.PERCENTAGE,
            categoryCombo = dummyCategoryCombo
        )
    )

    private val dummySections = listOf(
        DatasetSection(
            id = "sec1",
            name = "Enrollment",
            description = "Student enrollment data",
            sortOrder = 1,
            dataElements = listOf("de1")
        ),
        DatasetSection(
            id = "sec2",
            name = "Attendance",
            description = "Student attendance data",
            sortOrder = 2,
            dataElements = listOf("de2")
        )
    )

    private val dummyDatasets = listOf(
        Dataset(
            id = "1",
            name = "Primary Termly Tool",
            description = "Dataset for primary school termly data collection",
            categoryCombo = dummyCategoryCombo,
            formType = FormType.SECTION,
            sections = dummySections,
            dataElements = dummyDataElements
        ),
        Dataset(
            id = "2",
            name = "Pre-Primary Termly Tool",
            description = "Dataset for pre-primary school termly data collection",
            categoryCombo = dummyCategoryCombo,
            formType = FormType.SECTION,
            sections = dummySections,
            dataElements = dummyDataElements
        )
    )

    override suspend fun getDatasets(): Result<List<Dataset>> {
        delay(500) // Simulate network delay
        return Result.success(dummyDatasets)
    }

    override suspend fun syncDatasets(): Result<Unit> {
        delay(1000) // Simulate network delay
        return Result.success(Unit)
    }

    override suspend fun filterDatasets(period: String?, syncStatus: Boolean?): Result<List<Dataset>> {
        delay(500) // Simulate network delay
        return Result.success(dummyDatasets)
    }
}