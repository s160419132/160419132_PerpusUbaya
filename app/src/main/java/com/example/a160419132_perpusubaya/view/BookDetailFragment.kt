package com.example.a160419132_perpusubaya.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.databinding.FragmentBookDetailBinding
import com.example.a160419132_perpusubaya.model.Book
import com.example.a160419132_perpusubaya.util.BookWorker
import com.example.a160419132_perpusubaya.util.NotificationHelper
import com.example.a160419132_perpusubaya.util.loadImage
import com.example.a160419132_perpusubaya.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import java.util.concurrent.TimeUnit


class BookDetailFragment : Fragment(),ButtonUlasanListener, ButtonEdit {
    private lateinit var detailBookModel: BookDetailViewModel
    private lateinit var dataBinding:FragmentBookDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentBookDetailBinding>(inflater,R.layout.fragment_book_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailBookModel= ViewModelProvider(this).get(BookDetailViewModel::class.java)
        if(arguments != null){
            var isbn=BookDetailFragmentArgs.fromBundle(requireArguments()).isbn
            detailBookModel.getBookDetail(isbn)
        }

        ObserveDetailModel()

        /*btnUlasan.setOnClickListener {
            val action= BookDetailFragmentDirections.actionToReviewBookFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun ObserveDetailModel(){
        detailBookModel.BookLD.observe(viewLifecycleOwner, Observer {
            dataBinding.detailbuku=it
            dataBinding.ulasanListener=this
            dataBinding.listener = this

            /*txtDetailJudul.setText("Judul : "+it.judul)
            txtDetaiPenulis.setText("Penulis : "+it.penulis)
            txtDetailJmlHal.setText("Jumlah Halaman :"+it.jumlahHalaman.toString())
            txtDetailisbn.setText("Isbn : "+it.isbn)
            txtDetailDeskripsi.setText(it.deskripsi)
            txtDetailPenerbit.setText("Penerbit :"+ it.penerbit)
            imgDetailBook.loadImage(it.urlImage.toString(),progBarDetail)
            txtKategori.setText("Kategori :" + it.kategori)*/

        })
    }

    override fun onButtonUlasan(v: View) {
        var isbn= v.tag.toString()
        val action= BookDetailFragmentDirections.actionToReviewBookFragment(isbn)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonEdit(v: View, obj: Book) {
        view?.let {
            NotificationHelper(it.context)
                .createNotification("Deskripsi Buku diubah",
                    "Berhasil mengubah deskripsi buku")
        }

        val myWorkRequest = OneTimeWorkRequestBuilder<BookWorker>()
            .setInitialDelay(20, TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "Deskripsi Buku diubah",
                "message" to "Berhasil mengubah deskripsi buku"
            )).build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

        obj.deskripsi?.let { obj.isbn?.let { it1 -> detailBookModel.update(it, it1) } }
        Navigation.findNavController(v).popBackStack()
    }

}