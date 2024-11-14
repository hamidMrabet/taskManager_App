package com.example.mytaskmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (val context: Context,val listener: TaskClickListener):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val taskslist = ArrayList<Task>()

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.titleTxtView)
        val description = itemView.findViewById<TextView>(R.id.detailTxtView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item,
            parent, false
        )
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val item = taskslist[position]
        holder.title.text = item.title
        holder.title.isSelected = true
        holder.description.text = item.description
        holder.description.isSelected = true
        holder.itemView.setOnClickListener {
            listener.onTaskClicked(taskslist[holder.adapterPosition])
        }

    }


    override fun getItemCount(): Int {
        return taskslist.size
    }

    fun updateList(newList: List<Task>) {
        taskslist.clear()
        taskslist.addAll(newList)
        notifyDataSetChanged()
    }


    interface TaskClickListener {
        fun onTaskClicked(task: Task)
    }
}