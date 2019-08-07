package com.daffa.listsub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class RVAdapter(context: Context?, private val data: ArrayList<DataModel>) : RecyclerView.Adapter<ListHolder>() {
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
    private val tvSeri = itemView.tvSeri
    private val tvCpu = itemView.tvCpu
    private val tvGraphic = itemView.tvGraphic
    private val ivPost = itemView.ivPost
    private val item = itemView.itemContent

    fun bindData(context: Context?, data: DataModel){
        context?.let {
            Glide.with(context)
                .load(data.image)
                .error(R.drawable.err)
                .centerCrop()
                .into(ivPost)
            tvSeri.text = data.seri
            tvCpu.text = data.cpu
            tvGraphic.text = data.graphics
        }

        item.setOnClickListener {
            val intent = Intent(context, Detail::class.java)
            intent.putExtra("data", data)
            context?.startActivity(intent)
        }
    }

}