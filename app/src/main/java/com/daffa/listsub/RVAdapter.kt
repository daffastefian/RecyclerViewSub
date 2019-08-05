package com.daffa.listsub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(context: Context?, private val data: List<DataModel>) : RecyclerView.Adapter<ListHolder>(){
    private val mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bindData(mContext, data[position])
    }
}

class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val tvEx = itemView.tvEx

    fun bindData(context: Context?, data: DataModel){
        context?.let {

        }
    }
}