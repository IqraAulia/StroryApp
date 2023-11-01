package com.example.stroryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stroryapp.R
import com.example.stroryapp.databinding.ActivityMainBinding
import com.example.stroryapp.model.MainViewModel
import com.example.stroryapp.model.StoryAdapter
import com.example.stroryapp.model.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                viewModel.getStory(user.token).observe(this) { story ->
                    if (story != null) {
                        binding.progressBar.visibility = View.GONE
                        val adapter = StoryAdapter()
                        binding.rvReview.layoutManager = LinearLayoutManager(this)
                        adapter.submitData(lifecycle, story)
                        binding.rvReview.adapter = adapter
                        binding.halo.text = resources.getString(R.string.greeting, user.name)
                        Log.d("nama penggunan ", "onCreate: ${user.name}")
                    }
                }
            }
        }
        binding.menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    viewModel.getLogout()
                    finish()
                    true
                }

                R.id.map -> {
                    val intent = Intent(this@MainActivity, MapsActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}