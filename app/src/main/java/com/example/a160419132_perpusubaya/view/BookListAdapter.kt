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
import kotlinx.android.synthetic.main.fragment_book_list.view.*

class BookListAdapter(val bookList:ArrayList<Book>):RecyclerView.Adapter<BookListAdapter.BookViewHolder>(),ButtonDetailClickListener{
    class BookViewHolder(val view: BookListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
       val inflater = LayoutInflater.from(parent.context)
//       val v= inflater.inflate(R.layout.book_list_item,parent,false)
       val v= DataBindingUtil.inflate<BookListItemBinding>(inflater,R.layout.book_list_item,parent,false)

        return BookViewHolder(v)
    }

    fun updateBookList(newBooklist:List<Book>){
        bookList.clear()
        bookList.addAll(newBooklist)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.buku=bookList[position]
        holder.view.listenerBtn=this

        /*holder.view.apply {
            txtJudul.text= bookList[position].judul
            txtPenulis.text=bookList[position].penulis
            imgBuku.loadImage(bookList[position].urlImage.toString(),progressBar)
        }
        holder.view.btnDetail.setOnClickListener {
            val action=BookListFragmentDirections.actionBookDetailFragment(bookList[position].isbn.toString())
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
       return bookList.size
    }

    override fun onButtonDetailClick(v: View) {
        var isbn:String= v.tag.toString()
        val action=BookListFragmentDirections.actionBookDetailFragment(isbn)
        Navigation.findNavController(v).navigate(action)

    }
}