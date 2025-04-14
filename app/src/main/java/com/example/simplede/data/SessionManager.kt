package com.example.simplede.data

import com.example.simplede.domain.model.Dhis2Config


object SessionManager {
    private var currentSession: Dhis2Config? = null
    private val validCredentials = Dhis2Config(
        serverUrl = "https://play.dhis2.org/2.39.0",
        username = "admin",
        password = "district"
    )

    fun setSession(config: Dhis2Config) {
        currentSession = config
    }

    fun getSession(): Dhis2Config? = currentSession

    fun clearSession() {
        currentSession = null
    }

    fun isValidCredentials(config: Dhis2Config): Boolean {
        return config == validCredentials
    }
}
