package com.ruhul.letschatdemo.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ruhul.letschatdemo.R
import com.ruhul.letschatdemo.databinding.FragmentSignInEmailBinding
import com.ruhul.letschatdemo.utils.MPreference
import com.ruhul.letschatdemo.utils.isValidDestination

class SignInEmailFragment : Fragment() {

    private lateinit var binding: FragmentSignInEmailBinding
    private lateinit var auth: FirebaseAuth

    private val viewModel by activityViewModels<LogInViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInEmailBinding.inflate(inflater)
        auth = Firebase.auth


        binding.signUpButton.setOnClickListener {
            signUp()
        }


        binding.logInButton.setOnClickListener {
            signIn()
        }




        return binding.root
    }

    private fun signUp() {
        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEdiText.text.isNotEmpty()) {

            auth.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEdiText.text.toString().trim()
            )
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(), "success",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        } else {
            Toast.makeText(requireContext(), "check this field", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signIn() {
        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEdiText.text.isNotEmpty()) {
            auth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEdiText.text.toString().trim()
            ).addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(), "log in Success",
                        Toast.LENGTH_SHORT
                    ).show()

                    getFetchUserProfile()

                } else {
                    Toast.makeText(
                        requireContext(), "log in Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } else {
            Toast.makeText(requireContext(), "check this field", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFetchUserProfile() {
        viewModel.fetchUser(auth)
        val preference = MPreference(requireContext())
        Log.d("getFetchUserProfile", preference.getUid().toString())

        Log.d("getFetchUserProfile", "auth Id" + auth.uid.toString())

/*        if (findNavController().isValidDestination(R.id.FLogIn)){
            findNavController().navigate(R.id.action_FLogIn_to_FProfile)
        }*/
    }


}