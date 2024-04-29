package com.dicoding.asclepius.view.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.remote.ResultState
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.history.HistoryActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels()

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.show()

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        val result = intent.getStringExtra(EXTRA_RESULT)

        imageUri?.let {
            binding.resultImage.setImageURI(it)
        }

        result?.let {
            binding.resultText.text = it
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager

        viewModel.news.observe(this){ articles ->
            when(articles) {
                is ResultState.Success -> {
                    setNewsData(articles.data)
                }
                is ResultState.Error -> {
                    Log.e("ResultActivity", "$articles")
                    Toast.makeText(this, "$articles", Toast.LENGTH_SHORT).show()
                }
                is ResultState.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setNewsData(articles: List<ArticlesItem>){
        val adapter = NewsAdapter()
        adapter.submitList(articles)
        binding.rvNews.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_menu -> {
                val moveIntent = Intent(this@ResultActivity, HistoryActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}