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
import com.example.a160419132_perpusubaya.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_favourite_list.*
import kotlinx.android.synthetic.main.fragment_review_book.*


class ReviewBookFragment : Fragment() {
    private lateinit var viewModel:ReviewViewModel
    private val reviewBookAdapter = ReviewBookAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(ReviewViewModel::class.java)
        viewModel.loadListReview()
        ObserveViewModel()
    }
    fun ObserveViewModel(){
        viewModel.ReviewLD.observe(viewLifecycleOwner, Observer {
            reviewBookAdapter.updatelistReviewBook(it)
        })

        recViewReview.layoutManager=LinearLayoutManager(context)
        recViewReview.adapter=reviewBookAdapter
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progBarReview.visibility= View.VISIBLE
                recViewReview.visibility= View.GONE
            }else{
                progBarReview.visibility= View.GONE
                recViewReview.visibility= View.VISIBLE
            }
        })
    }
}