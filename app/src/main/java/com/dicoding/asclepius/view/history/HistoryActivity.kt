package com.dicoding.asclepius.view.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.History
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.helper.ViewModelFactory
import com.dicoding.asclepius.view.result.ResultActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: HistoryViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.show()

        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager

        viewModel.historyList.observe(this){
            setHistoryData(it)
        }
    }

    private fun setHistoryData(historys: List<History>){
        val adapter = HistoryAdapter()
        adapter.submitList(historys)
        binding.rvHistory.adapter = adapter
    }
}