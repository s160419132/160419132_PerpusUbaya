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
import com.example.a160419132_perpusubaya.viewmodel.FavouritelistViewModel
import kotlinx.android.synthetic.main.fragment_favourite_list.*
import kotlinx.android.synthetic.main.fragment_favourite_list.view.*

class FavouriteListFragment : Fragment() {
    private lateinit var viewModel:FavouritelistViewModel
    private val favouriteListAdapter= FavouriteListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_list, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(FavouritelistViewModel::class.java)
        viewModel.loadListFavourite()
        recViewFavorite.layoutManager= LinearLayoutManager(context)
        recViewFavorite.adapter=favouriteListAdapter

        ObserveViewModel()
    }

    fun ObserveViewModel(){
        viewModel.FavouritebooksLD.observe(viewLifecycleOwner, Observer {
            favouriteListAdapter.updateFavouriteList(it)
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progBarFavouriteFrag.visibility= View.VISIBLE
                recViewFavorite.visibility= View.GONE
            }else{
                progBarFavouriteFrag.visibility= View.GONE
                recViewFavorite.visibility= View.VISIBLE
            }
        })
    }

}