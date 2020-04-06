package com.codehangouts.newsfeed.data

import com.codehangouts.newsfeed.data.local.db.DbRepository
import com.codehangouts.newsfeed.data.local.prefs.PreferencesRepository
import com.codehangouts.newsfeed.data.remote.ApiRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DbRepository,
    private val preferencesRepository: PreferencesRepository
) : DataManager {
    fun getApiRepository(): ApiRepository {
        return apiRepository
    }

    fun getDbRepository(): DbRepository {
        return dbRepository
    }

    fun getPreferencesRepository(): PreferencesRepository {
        return preferencesRepository
    }
}
