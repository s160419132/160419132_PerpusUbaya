package com.example.a160419132_perpusubaya.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.viewmodel.BookListCategoryViewHolder
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_book_list_category.*


class BookListCategoryFragment : Fragment() {
    private lateinit var viewModel:BookListCategoryViewHolder
    private val bookListCategoryAdapter = BookListCategoryAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(BookListCategoryViewHolder::class.java)
        if(arguments != null){
            var category= BookListCategoryFragmentArgs.fromBundle(requireArguments()).category
            viewModel.getBookListCategory(category)
        }
        ObserveDetailModel()
        recBookCategoryList.layoutManager=LinearLayoutManager(context)
        recBookCategoryList.adapter=bookListCategoryAdapter
    }

    fun ObserveDetailModel(){
        viewModel.BooksLD.observe(viewLifecycleOwner, Observer {
            bookListCategoryAdapter.updateBookListCategory(it)
        })
    }
}