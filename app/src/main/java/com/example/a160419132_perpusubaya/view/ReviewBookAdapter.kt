package com.example.a160419132_perpusubaya.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.model.Review
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewBookAdapter(val listReviewBook: ArrayList<Review>):RecyclerView.Adapter<ReviewBookAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View): RecyclerView.ViewHolder(view)

    fun updatelistReviewBook(newlistReviewBook:List<Review>){
        listReviewBook.clear()
        listReviewBook.addAll(newlistReviewBook)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.review_list_item,parent,false)
        return ReviewViewHolder(v)
    }


    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.view.apply {
            txtReviewName.setText(listReviewBook[position].nama)
            txtReviewTgl.setText(listReviewBook[position].tanggal)
            txtReviewDeskripsi.setText(listReviewBook[position].komentar)
        }
    }

    override fun getItemCount(): Int {
        return  listReviewBook.size
    }
}