//package com.example.simplede.domain.usecase.dataentry
//
//import com.example.simplede.domain.repository.DataEntryRepository
//import javax.inject.Inject
//
//class SyncDataValuesUseCase @Inject constructor(
//    private val repository: DataEntryRepository
//) {
//    suspend operator fun invoke(instanceId: String): Result<Unit> {
//        return repository.syncDataValues(instanceId)
//    }
//}