package com.example.a160419132_perpusubaya.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.model.Category
import com.example.a160419132_perpusubaya.util.loadImage
import kotlinx.android.synthetic.main.category_list_item.view.*

class CategoryListAdapter(val categoriList:ArrayList<Category>):RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.category_list_item,parent,false)
        return  CategoryViewHolder(v)
    }
    fun updateCategoryList(newCategoryList:List<Category>){
        categoriList.clear()
        categoriList.addAll(newCategoryList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.view.apply {
            txtCategoryName.setText(categoriList[position].nama)
            imgCategory.loadImage(categoriList[position].url.toString(),progBarCategory)
            btnToCategoryBook.setOnClickListener {
                var category:String=categoriList[position].nama.toString()
                val action=CategoryListFragmentDirections.actionToBookListCategory(category)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoriList.size
    }
}