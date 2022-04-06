package com.example.a160419132_perpusubaya.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.model.Histori
import com.example.a160419132_perpusubaya.util.loadImage
import kotlinx.android.synthetic.main.history_list_item.view.*

class HistoryAdapter(val historiList: ArrayList<Histori>):RecyclerView.Adapter<HistoryAdapter.HistoriViewHolder>() {
    class HistoriViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v= inflater.inflate(R.layout.history_list_item,parent,false)
        return HistoriViewHolder(v)
    }
    fun updateHistoryList(newHistoryList:List<Histori>){
        historiList.clear()
        historiList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: HistoriViewHolder, position: Int) {
        holder.view.apply {
            txtHistoriJudul.setText(historiList[position].judul)
            txtHistoriPenulis.setText(historiList[position].penulis)
            txtHistoriTanggal.setText("tgl Booking :"+historiList[position].tglPinjam)
            imgHistory.loadImage(historiList[position].urlGambar.toString(),progbarHistori)
        }
    }

    override fun getItemCount(): Int {
        return historiList.size
    }

}