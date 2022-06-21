package com.example.a160419132_perpusubaya.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.databinding.FragmentReviewBookBinding
import com.example.a160419132_perpusubaya.model.Review
import com.example.a160419132_perpusubaya.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_favourite_list.*
import kotlinx.android.synthetic.main.fragment_review_book.*
import java.text.SimpleDateFormat
import java.util.*


class ReviewBookFragment : Fragment(),ButtonSendReviewListener {
    private lateinit var viewModel:ReviewViewModel
    private lateinit var isbn:String
    private lateinit var nama:String
    private val reviewBookAdapter = ReviewBookAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentReviewBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentReviewBookBinding>(inflater,R.layout.fragment_review_book, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(ReviewViewModel::class.java)
        if(arguments != null){
            isbn=ReviewBookFragmentArgs.fromBundle(requireArguments()).isbn
            viewModel.loadListReview(isbn)
            var shared= this.activity?.getSharedPreferences("test",Context.MODE_PRIVATE)
            var nrp = shared?.getString("nrp","")
            nama= viewModel.getnama(nrp.toString())
            Log.d("shared",nama)
        }
        dataBinding.reviewlistener=this
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

    override fun onButtonSendReview(v: View, komen: String) {
        var today = SimpleDateFormat("dd/MM/yyyy HH:mm ", Locale.getDefault()).format(Date())
        var newUlasan:Review= Review(isbn,nama,komen,today)


        Log.d("checkReview",newUlasan.toString())
        viewModel.insertReview(newUlasan)
        viewModel.loadListReview(isbn)
    }
}