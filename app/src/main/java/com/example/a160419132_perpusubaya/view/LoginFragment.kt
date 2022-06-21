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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.databinding.FragmentLoginBinding
import com.example.a160419132_perpusubaya.model.Login
import com.example.a160419132_perpusubaya.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), ButtonLoginListener {
    private lateinit var loginModel:LoginViewModel
    private lateinit var dataBinding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        dataBinding.loginlistener=this
    }

    override fun onButtonLogin(v: View, nrp: String, password: String) {
        var hasil =loginModel.inputLogin(nrp,password)
        Log.d("checkhasil",hasil)
        if(hasil=="ada"){
            val action =LoginFragmentDirections.actionToListBookFragment(nrp)
            Navigation.findNavController(v).navigate(action)
            val pref= activity?.getPreferences(Context.MODE_PRIVATE)
            var editor= pref?.edit()
            editor?.putString("NRP",nrp)
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