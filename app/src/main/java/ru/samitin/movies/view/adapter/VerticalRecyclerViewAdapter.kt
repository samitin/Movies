package ru.samitin.movies.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.movies.R
import ru.samitin.movies.entities.CardMovie
import ru.samitin.movies.entities.CategoryMovies

class VerticalRecyclerViewAdapter(val context: Context?,var arrayList:ArrayList<CategoryMovies>):
    RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRVViewHolder>() {

    private var onVerticalClickListener:OnVerticalClickListener?=null
    private var onHorisontalClickListener: OnHorisontalClickListener?=null

    fun setOnVerticalClickListener(onVerticalClickListener:OnVerticalClickListener){
        this.onVerticalClickListener=onVerticalClickListener
    }
    fun setOnHorisontalClickListener(onHorisontalClickListener:OnHorisontalClickListener){
        this.onHorisontalClickListener=onHorisontalClickListener
    }
    fun addCategoryModel(verticalModel: CategoryMovies){
        arrayList.add(verticalModel)
        notifyItemInserted(getItemCount()-1)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalRVViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_vertical,parent,false)
        return VerticalRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalRVViewHolder, position: Int) {
        val categoryMovies=arrayList.get(position)
        val title=categoryMovies.categoryName
        val singleItem : ArrayList<CardMovie> = categoryMovies.listMovies!!
        holder.textTitle1.text=title
        val horisontalrecyclerViewAdapter=HorisontalRecyclerViewAdapter(context!!,singleItem)
        horisontalrecyclerViewAdapter.setOnHorisontalClickListener(onHorisontalClickListener!!)
        holder.recyclerView1.setHasFixedSize(true)
        holder.recyclerView1.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        holder.recyclerView1.adapter=horisontalrecyclerViewAdapter
        holder.btnMore.setOnClickListener {
            onVerticalClickListener?.onClick(view = it)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class VerticalRVViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textTitle1: TextView
        val recyclerView1: RecyclerView
        val btnMore: Button
        init {
            textTitle1=itemView.findViewById<TextView>(R.id.textTitle1)
            recyclerView1=itemView.findViewById<RecyclerView>(R.id.recyclerview1)
            btnMore=itemView.findViewById<Button>(R.id.btnMore)
        }
    }
    interface OnVerticalClickListener{
        fun onClick(view : View)
    }
    interface OnHorisontalClickListener{
        fun onClick(model: CardMovie)
    }
}