package com.example.a160419132_perpusubaya.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    private lateinit var profileModel:ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref= activity?.getPreferences(Context.MODE_PRIVATE)
        var nrp = pref?.getString("NRP","default")
        profileModel= ViewModelProvider(this).get(ProfileViewModel::class.java)
        var nama= profileModel.getNama(nrp.toString())
        txtProfileNrp.setText("NRP : " +nrp)
        txtProfileNama.setText("Nama : " +nama)
    }
}