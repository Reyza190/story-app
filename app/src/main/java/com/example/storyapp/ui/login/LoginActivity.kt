package com.example.storyapp.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storyapp.ViewModelFactory
import com.example.storyapp.data.api.LoginResult
import com.example.storyapp.databinding.ActivityLoginBinding
import com.example.storyapp.ui.main.MainActivity
import com.example.storyapp.ui.register.RegisterActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //animation
        playAnimation()


        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (password.length >= 8) {
                    loginViewModel.login(
                        email, password
                    ).observe(this) { result ->
                        if (result != null) {
                            when (result) {
                                is com.example.storyapp.data.Result.Loading -> {
                                    showLoading(true)
                                }
                                is com.example.storyapp.data.Result.Success -> {
                                    showLoading(false)
                                    saveKey(result.data.loginResult)
                                    toastMessage(result.data.message)
                                }
                                is com.example.storyapp.data.Result.Error -> {
                                    showLoading(false)
                                    Toast.makeText(this, result.error, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }

                }
            } else {
                toastMessage("Lengkapi form")
            }

        }
    }

    private fun saveKey(data: LoginResult?) {
        val pref = com.example.storyapp.data.UserPreference(applicationContext)
        pref.setToken(data?.token.toString())
        pref.setLoginStatus(true)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.logo, View.TRANSLATION_Y, -500f, 30f).apply {
            duration = 1000
        }.start()

        val edtEmail = ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(500)
        val edtPassword =
            ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(500)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
        val btnRegister =
            ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(edtEmail, edtPassword, btnLogin, btnRegister)
            start()
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}