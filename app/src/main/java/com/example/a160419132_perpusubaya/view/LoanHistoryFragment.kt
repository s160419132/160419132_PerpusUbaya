package com.example.a160419132_perpusubaya.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.viewmodel.HistoriViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_loan_history.*


class LoanHistoryFragment : Fragment() {
    private lateinit var viewModelHistory:HistoriViewModel
    private val historiListAdapter = HistoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelHistory=ViewModelProvider(this).get(HistoriViewModel::class.java)
        viewModelHistory.gethistory()

        recViewHistori.layoutManager=LinearLayoutManager(context)
        recViewHistori.adapter=historiListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModelHistory.HistoryLD.observe(viewLifecycleOwner, Observer {
            historiListAdapter.updateHistoryList(it)
        })

        viewModelHistory.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){

                recViewHistori.visibility= View.GONE
            }else{

                recViewHistori.visibility= View.VISIBLE
            }
        })
    }

}