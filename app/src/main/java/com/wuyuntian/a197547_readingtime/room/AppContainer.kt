package com.wuyuntian.a197547_readingtime.room

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val planDatabase:PlanDatabase
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val planDatabase:PlanDatabase by lazy {
        planDatabase
    }

}
