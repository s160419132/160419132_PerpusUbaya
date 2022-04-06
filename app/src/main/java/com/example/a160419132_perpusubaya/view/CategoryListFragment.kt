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
import com.example.a160419132_perpusubaya.viewmodel.CategoryListViewModel
import kotlinx.android.synthetic.main.fragment_category_list.*


class CategoryListFragment : Fragment() {

        private lateinit var viewModel:CategoryListViewModel
        private val categoryListAdapter= CategoryListAdapter(arrayListOf())

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(CategoryListViewModel::class.java)
        viewModel.getCategory()
        recViewCategory.layoutManager = LinearLayoutManager(context)
        recViewCategory.adapter=categoryListAdapter

        ObserveViewModel()
    }

    fun ObserveViewModel(){
        viewModel.categoryLD.observe(viewLifecycleOwner, Observer {
            categoryListAdapter.updateCategoryList(it)
        })
    }
}