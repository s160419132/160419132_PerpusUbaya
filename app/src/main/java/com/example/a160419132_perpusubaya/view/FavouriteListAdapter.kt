package com.example.a160419132_perpusubaya.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class FavouriteListAdapter(val favouriteList:ArrayList<Book>):RecyclerView.Adapter<FavouriteListAdapter.FavouriteViewHolder>() {
    class FavouriteViewHolder(var view: View): RecyclerView.ViewHolder(view)

    fun updateFavouriteList(newFavouriteList:List<Book>){
        favouriteList.clear()
        favouriteList.addAll(newFavouriteList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v= inflater.inflate(R.layout.book_list_item,parent,false)
        return FavouriteViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.view.apply {
            txtJudul.text= favouriteList[position].judul
            txtPenulis.text=favouriteList[position].penulis
            imgBuku.loadImage(favouriteList[position].urlImage.toString(),progressBar)
        }
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }
}