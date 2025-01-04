package com.nitishsharma.customsnacks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nitishsharma.customsnacks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListeners()
    }

    private fun initClickListeners() {
        binding.apply {
            showSnackBarButton.setOnClickListener {
                snackBarHost.showSnack(
                    snackMessage = "Hey, Testing Snacky!",
                    showSuccessErrorIcon = true,
                    isSuccess = true
                )
            }
        }
    }
}