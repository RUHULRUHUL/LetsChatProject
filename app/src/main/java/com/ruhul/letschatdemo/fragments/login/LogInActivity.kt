package com.ruhul.letschatdemo.fragments.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ruhul.letschatdemo.R
import com.ruhul.letschatdemo.databinding.ActivityLogInBinding
import com.ruhul.letschatdemo.ui.activities.MainActivity


class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            signUp()
        }


        binding.logInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signUp() {
        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEdiText.text.isNotEmpty()) {

            auth.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEdiText.text.toString().trim()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                           // findNavController().navigate(R.id.action_FVerify_to_FProfile)

                    } else {
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        } else {
            Toast.makeText(this, "check this field", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signIn() {
        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEdiText.text.isNotEmpty()) {
            auth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEdiText.text.toString().trim()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(
                        this, "log in Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } else {
            Toast.makeText(this, "check this field", Toast.LENGTH_SHORT).show()
        }
    }

}