package com.example.a160419132_perpusubaya.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.util.loadImage
import com.example.a160419132_perpusubaya.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookDetailFragment : Fragment() {
    private lateinit var detailBookModel: BookDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailBookModel= ViewModelProvider(this).get(BookDetailViewModel::class.java)
        if(arguments != null){
            var isbn=BookDetailFragmentArgs.fromBundle(requireArguments()).isbn
            detailBookModel.getBookDetail(isbn)
        }
        ObserveDetailModel()
        btnUlasan.setOnClickListener {
            val action= BookDetailFragmentDirections.actionToReviewBookFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun ObserveDetailModel(){
        detailBookModel.BookLD.observe(viewLifecycleOwner, Observer {
            txtDetailJudul.setText("Judul : "+it.judul)
            txtDetaiPenulis.setText("Penulis : "+it.penulis)
            txtDetailJmlHal.setText("Jumlah Halaman :"+it.jumlahHalaman.toString())
            txtDetailisbn.setText("Isbn : "+it.isbn)
            txtDetailDeskripsi.setText(it.deskripsi)
            txtDetailPenerbit.setText("Penerbit :"+ it.penerbit)
            imgDetailBook.loadImage(it.urlImage.toString(),progBarDetail)
            txtKategori.setText("Kategori :" + it.kategori)

        })
    }

}