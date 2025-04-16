//package com.example.simplede.domain.usecase.dataentry
//
//import com.example.simplede.domain.model.ValueHistory
//import com.example.simplede.domain.repository.DataEntryRepository
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class GetValueHistoryUseCase @Inject constructor(
//    private val repository: DataEntryRepository
//) {
//    suspend operator fun invoke(
//        instanceId: String,
//        dataElement: String,
//        categoryOptionCombo: String
//    ): Flow<List<ValueHistory>> {
//        return repository.getValueHistory(
//            instanceId = instanceId,
//            dataElement = dataElement,
//            categoryOptionCombo = categoryOptionCombo
//        )
//    }
//}