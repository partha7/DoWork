package com.example.todo.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.models.ChoresData
import com.example.todo.data.models.Priority
import java.util.zip.Inflater

class ChoresListAdapter(private val context: Context): RecyclerView.Adapter<ChoresViewHolder>() {

    private var choresList: List<ChoresData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoresViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_layout, parent, false)
        return ChoresViewHolder(view)
    }

    override fun getItemCount(): Int {
        return choresList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ChoresViewHolder, position: Int) {

        holder.apply {
            titleTextView.text = choresList?.get(position)?.title
            descriptionTextView.text = choresList?.get(position)?.description
            when (choresList?.get(position)?.priority) {
                Priority.HIGH -> {
                    priorityIndicator.setCardBackgroundColor(context.getColor(R.color.red))
                }
                Priority.MEDIUM -> {
                    priorityIndicator.setCardBackgroundColor(context.getColor(R.color.yellow))
                }
                Priority.LOW -> {
                    priorityIndicator.setCardBackgroundColor(context.getColor(R.color.green))
                }
                else -> {}
            }
        }
    }

    fun setData(choresList: List<ChoresData>) {
        println("test111: ${choresList}")
        this.choresList = choresList
        notifyDataSetChanged()
    }

}

class ChoresViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val titleTextView: TextView = itemView.findViewById(R.id.title_txt)
    val descriptionTextView: TextView = itemView.findViewById(R.id.description_txt)
//    val prioritySpinner: Spinner = itemView.findViewById(R.id.prio)
    val priorityIndicator: CardView = itemView.findViewById(R.id.priority_indicator)

}