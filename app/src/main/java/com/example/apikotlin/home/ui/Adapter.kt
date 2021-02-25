package com.example.apikotlin.home.ui

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apikotlin.R
import com.example.apikotlin.databinding.RvlayoutBinding
import com.example.apikotlin.home.data.Data
import com.squareup.picasso.Picasso

class Adapter(var context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var data: ArrayList<Data>? = null

    inner class ViewHolder(var itemBinding: RvlayoutBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(item: Data?) {
            if (item != null) {
                if (item.firstName != null) {
                    itemBinding.tvFirstName.text = item.firstName
                }
                if (item.lastName != null) {
                    itemBinding.tvLastName.text = item.lastName
                }
                if (item.email != null) {
                    itemBinding.tvEmail.text = item.email
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //    val view = LayoutInflater.from(parent.context).inflate(R.layout.rvlayout,parent,false)
        val binding : RvlayoutBinding = DataBindingUtil.inflate((context as Activity).layoutInflater,
                R.layout.rvlayout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
        Picasso.with(context).load(data?.get(position)?.avatar).into(holder.itemBinding.ivAvatar)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun setData(list: ArrayList<Data>) {
        data = list
        notifyDataSetChanged()
    }

}