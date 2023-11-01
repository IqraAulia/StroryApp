package com.example.stroryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.stroryapp.R
import com.example.stroryapp.data.Result
import com.example.stroryapp.databinding.ActivityDetailBinding
import com.example.stroryapp.model.DetailViewModel
import com.example.stroryapp.model.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                val data = intent.getStringExtra(EXTRA_ID)
                if (data != null) {
                    viewModel.getDetail(user.token, data).observe(this) { story ->
                        if (story != null) {
                            when (story) {
                                is Result.Loading -> {
                                    binding.progressBar.visibility = View.VISIBLE

                                }

                                is Result.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    binding.tvDetailName.text = story.data.story?.name
                                    binding.textDescription.text = story.data.story?.description
                                    Glide.with(this).load(story.data.story?.photoUrl)
                                        .into(binding.imageDetailPhoto)

                                }

                                is Result.Error -> {
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        this,
                                        R.string.erorGagal.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}