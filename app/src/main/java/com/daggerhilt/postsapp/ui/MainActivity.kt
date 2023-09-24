package com.daggerhilt.postsapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.daggerhilt.postsapp.databinding.ActivityMainBinding
import com.daggerhilt.postsapp.databinding.DialogAddPostBinding
import com.daggerhilt.postsapp.ui.adapter.PostAdapter
import com.daggerhilt.postsapp.viewmodels.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.response.observe(this) {
            binding.rvMain.adapter = PostAdapter(it)
        }

        viewModel.post.observe(this) {
            Toast.makeText(this, "Post Added", Toast.LENGTH_SHORT).show()
        }

        binding.fabMain.setOnClickListener {
            showAddPostDialog()
        }
    }

    private fun showAddPostDialog() {
        val dialogBinding = DialogAddPostBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Custom Dialog")
            .setView(dialogBinding.root)
            .show()

        val title = dialogBinding.etTitle.text.toString()
        val body = dialogBinding.etDescription.text.toString()

        dialogBinding.btnAdd.setOnClickListener {
            viewModel.addPost(title, body)
            dialog.dismiss()
        }

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}