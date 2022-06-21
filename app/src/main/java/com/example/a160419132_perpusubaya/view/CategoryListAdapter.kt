package com.example.a160419132_perpusubaya.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160419132_perpusubaya.R
import com.example.a160419132_perpusubaya.databinding.CategoryListItemBinding
import com.example.a160419132_perpusubaya.model.Category
import com.example.a160419132_perpusubaya.util.loadImage
import kotlinx.android.synthetic.main.category_list_item.view.*

class CategoryListAdapter(val categoriList:ArrayList<Category>):RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>(),ButtonBookListCategory{
    class CategoryViewHolder(var view: CategoryListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<CategoryListItemBinding>(inflater,R.layout.category_list_item,parent,false)
        return  CategoryViewHolder(v)
    }
    fun updateCategoryList(newCategoryList:List<Category>){
        categoriList.clear()
        categoriList.addAll(newCategoryList)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.view.category=categoriList[position]
        holder.view.listenerCategory=this
        /*holder.view.apply {
            txtCategoryName.setText(categoriList[position].nama)
            imgCategory.loadImage(categoriList[position].url.toString(),progBarCategory)
            btnToCategoryBook.setOnClickListener {
                var category:String=categoriList[position].nama.toString()
                val action=CategoryListFragmentDirections.actionToBookListCategory(category)
                Navigation.findNavController(it).navigate(action)
            }
        }*/
    }

    override fun getItemCount(): Int {
        return categoriList.size
    }

    override fun onButtonBookListCategory(v: View) {
        var category:String=v.tag.toString()
        val action=CategoryListFragmentDirections.actionToBookListCategory(category)
        Navigation.findNavController(v).navigate(action)
    }
}