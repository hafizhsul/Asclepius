package com.dicoding.asclepius.data.local

import android.app.Application

class HistoryRepository(application: Application) {
    private val historyDao = AppDatabase.getDatabase(application).historyDao()

    suspend fun addHistory(history: History) {
        historyDao.addHistory(history)
    }

    fun getHistory(): List<History> = historyDao.loadHistory()
}