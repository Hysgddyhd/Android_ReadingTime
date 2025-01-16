package com.wuyuntian.a197547_readingtime

import android.app.Application
import android.util.Log
import androidx.work.Configuration

class RTApplication: Application(), Configuration.Provider {
        override fun onCreate() {
        super.onCreate()
        backupCreator = BackupCreator()
    }

    override val workManagerConfiguration by lazy {
        Configuration.Builder().setMinimumLoggingLevel(Log.INFO).build()
    }
}