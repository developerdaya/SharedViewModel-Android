package com.developerdaya.mvvmexample.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.developerdaya.mvvmexample.R
import com.developerdaya.mvvmexample.databinding.ItemEmployeeBinding
import com.developerdaya.mvvmexample.model.CountryListModel
import okhttp3.internal.notify

class EmployeeAdapter(var isFirst:Boolean,var context1: Context,val employees: ArrayList<CountryListModel.Data>, var onClick: EmployeeAdapter.OnCLick) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(inflater, parent, false)
        return EmployeeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(isFirst,position,0,300,context1,employees[position],onClick)
    }

    override fun getItemCount(): Int = employees.size
    class EmployeeViewHolder(var binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(isFirst:Boolean,position: Int,lastClickTime1:Long,DOUBLE_CLICK_DELAY1:Long,context1: Context,employee: CountryListModel.Data,onClick: OnCLick)
        {
            if (isFirst)
            {
                if (employee.isSelected==true){
                    binding.root.setBackgroundColor(context1.resources.getColor(R.color.red))
                }else{
                    binding.root.setBackgroundColor(context1.resources.getColor(android.R.color.white))
                }
            }

             var lastClickTime: Long = lastClickTime1
             val DOUBLE_CLICK_DELAY =DOUBLE_CLICK_DELAY1
            binding.textName.text = employee.state

            binding.root.setOnClickListener {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime < DOUBLE_CLICK_DELAY)
                {
                    if(employee.isSelected==true)
                    {
                        onClick.onDoubleClick(position,employee,false)

                    }
                    else{
                        onClick.onDoubleClick(position,employee,true)

                    }
                }
                else{
                    onClick.onClick(employee)
                }
                lastClickTime = currentTime
            }

        }
    }
    interface OnCLick
    {
        fun onClick(data: CountryListModel.Data)
        fun onDoubleClick(position: Int,employee: CountryListModel.Data,isSelected:Boolean)

    }
}
