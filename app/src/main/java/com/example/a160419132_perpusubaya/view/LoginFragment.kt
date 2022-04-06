package com.example.a160419132_perpusubaya.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private lateinit var loginModel:LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        loginModel.dataLogin()
        btnLogin.setOnClickListener {
            var NRP= txtNRP.text.toString()
            var pass= txtPass.text.toString()
            var hasil =loginModel.inputLogin(NRP,pass)
//            Log.d("Checkdata",GlobalData.logindata.toString())
            Log.d("checkhasil",hasil)
            if(hasil=="ada"){
                val action =LoginFragmentDirections.actionToListBookFragment(NRP)
                Navigation.findNavController(it).navigate(action)
                val pref= activity?.getPreferences(Context.MODE_PRIVATE)
                var editor= pref?.edit()
                editor?.putString("NRP",NRP)
                editor?.commit()

            }
            else
            {
                val builder=AlertDialog.Builder(activity)
                builder
                    .setTitle("Login")
                    .setMessage("username/Password incorrect!")
                    .setNegativeButton("TRY AGAIN",null).create().show()

            }
        }

    }
}