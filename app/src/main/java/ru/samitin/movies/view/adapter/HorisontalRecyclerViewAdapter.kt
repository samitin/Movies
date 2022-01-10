package ru.samitin.movies.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.movies.R
import ru.samitin.movies.entities.CardMovie

class HorisontalRecyclerViewAdapter(val context: Context, val arrayList: ArrayList<CardMovie>) :
    RecyclerView.Adapter<HorisontalRecyclerViewAdapter.HorisontalViewHolder>() {

    private var onHorisontalClickListener: VerticalRecyclerViewAdapter.OnHorisontalClickListener?=null

    fun setOnHorisontalClickListener(onHorisontalClickListener: VerticalRecyclerViewAdapter.OnHorisontalClickListener){
        this.onHorisontalClickListener=onHorisontalClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorisontalViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_horisontal,parent,false)
        return HorisontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorisontalViewHolder, position: Int) {
        val cardMovie=arrayList.get(position)
        holder.nameCard.text=cardMovie.name
        holder.dateCard.text=cardMovie.date
        holder.rating.text=cardMovie.rating
        holder.imageCard.setImageResource(R.drawable.movie_icon)
        holder.itemView.setOnClickListener {
            onHorisontalClickListener?.onClick(cardMovie)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class HorisontalViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageCard:ImageView
        val nameCard:TextView
        val dateCard:TextView
        val rating:TextView
        init {
            imageCard=itemView.findViewById(R.id.image_Card)
            nameCard=itemView.findViewById(R.id.name_card)
            dateCard=itemView.findViewById(R.id.date_card)
            rating=itemView.findViewById(R.id.rating_card)
        }
    }
}