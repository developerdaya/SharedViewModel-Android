package com.developerdaya.mvvmexample.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.mvvmexample.databinding.FragmentScreen1StateListBinding
import com.developerdaya.mvvmexample.model.CountryListModel
import com.developerdaya.mvvmexample.ui.adapter.EmployeeAdapter
import com.developerdaya.mvvmexample.ui.viewmodel.EmployeesViewModel
import com.developerdaya.mvvmexample.ui.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log


class Screen1StateList : Fragment() {
    private lateinit var employeeAdapter: EmployeeAdapter
    lateinit var viewModel: EmployeesViewModel
    private var _binding: FragmentScreen1StateListBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmployeesViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        initViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()

    }
    private fun initViews() {
    }

    private fun observer() {
        sharedViewModel.selectedData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                employeeAdapter = EmployeeAdapter(true,requireContext(),data,object : EmployeeAdapter.OnCLick{
                    override fun onClick(data: CountryListModel.Data) {
                        val fragmentManager: FragmentManager = parentFragmentManager
                        val detailsFragment = fragmentManager.findFragmentByTag("DetailsFragment") as? DetailsFragment
                        if (detailsFragment != null) {
                            detailsFragment.updateData(requireContext(), data)
                        } else {
                            // Optional: If the fragment is null, you can log a message or handle it accordingly
                            Toast.makeText(requireContext(), "DetailsFragment not found", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onDoubleClick(position:Int,employee: CountryListModel.Data,isSelected:Boolean)
                    {
                           // sharedViewModel.selectedData.value?.get(position)?.isSelected = isSelected
                        sharedViewModel.updateSelection(position, isSelected)
                        Log.d("########", "onDoubleClick: $employee")


                    }

                })
                binding.recyclerView1.adapter = employeeAdapter


            }
        })


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScreen1StateListBinding.inflate(inflater, container, false)
        return binding.root
    }


}