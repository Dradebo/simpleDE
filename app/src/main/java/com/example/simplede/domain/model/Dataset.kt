package com.example.simplede.domain.model


data class Dataset(
    val id: String,
    val name: String,
    val description: String?,
    //val periodType: PeriodType,
    val categoryCombo: CategoryCombo,
    val formType: FormType,
    //val lastUpdated: Date,
    val sections: List<DatasetSection>,
    val dataElements: List<DataElement>
)

data class DatasetSection(
    val id: String,
    val name: String,
    val description: String?,
    val sortOrder: Int,
    val dataElements: List<String>
)

data class CategoryCombo(
    val id: String,
    val name: String,
    val categories: List<Category>
)

data class Category(
    val id: String,
    val name: String,
    val options: List<CategoryOption>
)

data class CategoryOption(
    val id: String,
    val name: String,
    val code: String?
)

data class DataElement(
    val id: String,
    val name: String,
    val valueType: ValueType,
    //val optionSet: OptionSet?,
    val categoryCombo: CategoryCombo
)

enum class FormType {
    DEFAULT,
    SECTION,
    CUSTOM
}

enum class ValueType {
    TEXT,
    LONG_TEXT,
    NUMBER,
    INTEGER,
    INTEGER_POSITIVE,
    INTEGER_NEGATIVE,
    INTEGER_ZERO_OR_POSITIVE,
    PERCENTAGE,
    UNIT_INTERVAL,
    DATE,
    DATETIME,
    TIME,
    BOOLEAN,
    TRUE_ONLY,
    YES_NO,
    FILE_RESOURCE,
    COORDINATE,
    PHONE_NUMBER,
    EMAIL,
    URL,
    IMAGE,
    AGE,
    OPTION_SET
}
