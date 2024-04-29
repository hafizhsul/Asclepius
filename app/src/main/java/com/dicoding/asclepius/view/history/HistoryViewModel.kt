package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.local.History
import com.dicoding.asclepius.data.local.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application = Application()): ViewModel() {

    private val historyRepository = HistoryRepository(application)

    var historyList: MutableLiveData<List<History>> = MutableLiveData()

    init {
        historyList.value = getHistory()
    }

    private fun getHistory(): List<History> = historyRepository.getHistory()

    fun addHistory(history: History) {
        viewModelScope.launch {
            historyRepository.addHistory(history)
        }
    }
}