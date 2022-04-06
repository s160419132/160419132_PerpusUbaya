package com.example.a160419132_perpusubaya.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.viewmodel.BookListViewModel
import com.example.a160419132_perpusubaya.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookListFragment : Fragment() {

    private lateinit var viewModel:BookListViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nrp =""
        if(arguments != null){
            nrp=BookListFragmentArgs.fromBundle(requireArguments()).nrp
        }
        Log.d("check4",nrp)
        if (nrp == "belum"){
            findNavController().navigate(R.id.loginFragment)
        }
        else
        {

        }
        viewModel= ViewModelProvider(this).get(BookListViewModel::class.java)
        viewModel.loadlist()
        observeViewModel()
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = bookListAdapter
    }

    fun observeViewModel(){

        viewModel.BooksLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){

                progbar.visibility= View.VISIBLE
                recView.visibility= View.GONE
            }else{
                progbar.visibility= View.GONE
                recView.visibility= View.VISIBLE
            }
        })
    }

}