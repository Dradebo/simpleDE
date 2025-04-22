package com.example.simplede.data.repositoryImpl


import com.example.simplede.data.SessionManager
import com.example.simplede.domain.model.Dhis2Config
import com.example.simplede.domain.repository.AuthRepository
import kotlinx.coroutines.delay


class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(config: Dhis2Config): Result<Unit> {
        // Simulate network delay
        delay(1000)
        return if (SessionManager.isValidCredentials(config)) {
            SessionManager.setSession(config)
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun getSessionInfo(): Dhis2Config? {
        delay(500) // Simulate network delay
        return SessionManager.getSession()
    }

    override suspend fun verifyCredentials(): Boolean {
        delay(500) // Simulate network delay
        val session = SessionManager.getSession()
        return session != null && SessionManager.isValidCredentials(session)
    }
}