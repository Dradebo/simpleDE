//package com.example.simplede.domain.usecase.dataentry
//
//import com.example.simplede.domain.model.DataValue
//import com.example.simplede.domain.repository.DataEntryRepository
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class GetDataValuesUseCase @Inject constructor(
//    private val repository: DataEntryRepository
//) {
//    suspend operator fun invoke(instanceId: String, section: String? = null): Flow<List<DataValue>> {
//        return repository.getDataValues(instanceId, section)
//    }
//}