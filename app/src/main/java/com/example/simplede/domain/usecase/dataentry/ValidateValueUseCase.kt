//package com.example.simplede.domain.usecase.dataentry
//
//import com.example.simplede.domain.model.ValidationResult
//import com.example.simplede.domain.repository.DataEntryRepository
//import javax.inject.Inject
//
//class ValidateValueUseCase @Inject constructor(
//    private val repository: DataEntryRepository
//) {
//    suspend operator fun invoke(
//        instanceId: String,
//        dataElement: String,
//        categoryOptionCombo: String,
//        value: String
//    ): ValidationResult {
//        return repository.validateValue(
//            instanceId = instanceId,
//            dataElement = dataElement,
//            categoryOptionCombo = categoryOptionCombo,
//            value = value
//        )
//    }
//}