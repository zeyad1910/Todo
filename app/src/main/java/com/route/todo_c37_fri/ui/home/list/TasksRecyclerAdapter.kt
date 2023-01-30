package com.route.todo_c37_fri.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.todo_c37_fri.database.model.Task
import com.route.todo_c37_fri.databinding.ItemTaskBinding

class TasksRecyclerAdapter(var items: List<Task>?):RecyclerView.Adapter<TasksRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int  = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.title.text = items?.get(position)?.title
        holder.viewBinding.desc.text = items?.get(position)?.description
    }

    fun changeData(newListOfTasks:List<Task>?){
        items = newListOfTasks;
        notifyDataSetChanged();
    }
    class ViewHolder(val viewBinding:ItemTaskBinding)
        :RecyclerView.ViewHolder(viewBinding.root){

    }
}