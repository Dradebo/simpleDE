//package com.example.simplede.domain.usecase.dataentry
//
//import com.example.simplede.domain.repository.CompletionStatus
//import com.example.simplede.domain.repository.DataEntryRepository
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class GetCompletionStatusUseCase @Inject constructor(
//    private val repository: DataEntryRepository
//) {
//    suspend operator fun invoke(instanceId: String): Flow<CompletionStatus> {
//        return repository.getCompletionStatus(instanceId)
//    }
//}