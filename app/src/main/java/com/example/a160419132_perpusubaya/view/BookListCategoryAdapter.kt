package com.example.a160419132_perpusubaya.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.databinding.BookListItemBinding
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListCategoryAdapter(val bookListCategory:ArrayList<Book>):RecyclerView.Adapter<BookListCategoryAdapter.SpecificCategoryHolder>(),ButtonDetailClickListener{
    class SpecificCategoryHolder(var view: BookListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v= DataBindingUtil.inflate<BookListItemBinding>(inflater,R.layout.book_list_item,parent,false)
        return SpecificCategoryHolder(v)
    }
    fun updateBookListCategory(newBooklistCategory:List<Book>){
        bookListCategory.clear()
        bookListCategory.addAll(newBooklistCategory)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SpecificCategoryHolder, position: Int) {
        holder.view.buku=bookListCategory[position]
        holder.view.listenerBtn=this
        /*holder.view.apply {
            txtJudul.text= bookListCategory[position].judul
            txtPenulis.text=bookListCategory[position].penulis
            imgBuku.loadImage(bookListCategory[position].urlImage.toString(),progressBar)
        }
        holder.view.btnDetail.setOnClickListener {
            val action=BookListCategoryFragmentDirections.actionBookDetailFragment2(bookListCategory[position].isbn.toString())
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return  bookListCategory.size
    }

    override fun onButtonDetailClick(v: View) {
        var id=v.tag.toString()
        val action=BookListCategoryFragmentDirections.actionBookDetailFragment2(id)
        Navigation.findNavController(v).navigate(action)
    }

}