package com.route.todo_c37_fri.ui.home.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todo_c37_fri.database.MyDataBase
import com.route.todo_c37_fri.databinding.FragmentListBinding
import java.util.*

class TasksListFragment:Fragment() {
    lateinit var viewBinding:FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        viewBinding = FragmentListBinding.inflate(inflater,
        container,false)
        return viewBinding.root
    }
    lateinit var tasksAdapter:TasksRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasksAdapter = TasksRecyclerAdapter(null)
        viewBinding.tasksRecycler.adapter = tasksAdapter
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if(selected){
                currentDate.set(Calendar.DAY_OF_MONTH,date.day)
                currentDate.set(Calendar.MONTH,date.month-1)
                currentDate.set(Calendar.YEAR,date.year)
                loadTasks();
            }
        }
        viewBinding.calendarView.selectedDate = CalendarDay.today()

       // loadTasks()
    }
    val currentDate = Calendar.getInstance()
    init {
        currentDate.set(Calendar.HOUR,0)
        currentDate.set(Calendar.MINUTE,0)
        currentDate.set(Calendar.SECOND,0)
        currentDate.set(Calendar.MILLISECOND,0)
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }


    fun loadTasks(){
        if(!isResumed){
            return
        }
       val tasks =  MyDataBase.getDataBase(requireActivity())
            .tasksDao()
            .getTasksByDate(currentDate.timeInMillis);
        tasksAdapter.changeData(tasks)

    }
}