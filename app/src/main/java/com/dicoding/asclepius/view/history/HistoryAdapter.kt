package com.dicoding.asclepius.view.history

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.data.local.History
import com.dicoding.asclepius.databinding.ListItemHistoryBinding
import com.dicoding.asclepius.view.result.ResultActivity

class HistoryAdapter: ListAdapter<History, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.MyViewHolder {
        val binding = ListItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.MyViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(history)
    }

    inner class MyViewHolder(val binding: ListItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            val imageUri = Uri.parse(history.uri)
            binding.imgItemPhoto.setImageURI(imageUri)
            binding.tvItemUser.text = history.result
            binding.tvItemUrl.text = history.date

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ResultActivity::class.java)
                intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, history.uri)
                intent.putExtra(ResultActivity.EXTRA_RESULT, history.result)
                binding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }
}