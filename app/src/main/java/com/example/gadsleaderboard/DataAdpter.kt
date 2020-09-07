package com.example.gadsleaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DataAdpter (val context: Context) : RecyclerView.Adapter<DataAdpter.MyViewHolder>() {

    var modelList: List<Model> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = modelList.get(position).title
        holder.detail.text = modelList.get(position).detail
        holder.country.text = modelList.get(position).country
        Glide.with(context).load(modelList.get(position).image)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

     fun setModelListItems(modelList: List<Model>) {
        this.modelList = modelList;
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val name: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.picture)
        val detail: TextView = itemView!!.findViewById(R.id.detail)
        val country: TextView = itemView!!.findViewById(R.id.country)

    }
}

