//package com.example.simplede.domain.usecase.dataentry
//
//import com.example.simplede.domain.model.DataValue
//import com.example.simplede.domain.repository.DataEntryRepository
//import javax.inject.Inject
//
//class SaveDataValueUseCase @Inject constructor(
//    private val repository: DataEntryRepository
//) {
//    suspend operator fun invoke(
//        instanceId: String,
//        dataElement: String,
//        categoryOptionCombo: String,
//        value: String?,
//        comment: String? = null
//    ): Result<DataValue> {
//        return repository.saveDataValue(
//            instanceId = instanceId,
//            dataElement = dataElement,
//            categoryOptionCombo = categoryOptionCombo,
//            value = value,
//            comment = comment
//        )
//    }
//}