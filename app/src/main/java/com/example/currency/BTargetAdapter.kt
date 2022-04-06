package com.example.currency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BTargetAdapter(private val barangtarget: List) :
    RecyclerView.Adapter() {

    //viewholder adalah class yang menyimpan referensi layout item list
    class BTargetViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvbarang: TextView = view.findViewById(R.id.tvbarang)
        val tvharga: TextView = view.findViewById(R.id.tvharga)
    }

    //membuat layout item_list sebagai item untuk recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): BTargetViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)

        return BTargetViewHolder(view)
    }

    //pengaturan data pada item setiap list dari recycleview
    override fun onBindViewHolder(holder: BTargetViewHolder, position: Int){
        holder.tvbarang.text = barangtarget[position].barang
        holder.tvharga.text = barangtarget[position].harga
    }

    //mengambil nilai panjang dari data
    override fun getItemCount(): Int{
        return barangtarget.size
    }
}

