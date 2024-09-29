package com.developerdaya.mvvmexample.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.mvvmexample.databinding.FragmentScreen2StateListBinding
import com.developerdaya.mvvmexample.model.CountryListModel
import com.developerdaya.mvvmexample.ui.adapter.EmployeeAdapter
import com.developerdaya.mvvmexample.ui.viewmodel.SharedViewModel

class Screen2StateList : Fragment() {
    private var _binding: FragmentScreen2StateListBinding? = null
    private val binding get() = _binding!!
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var sharedViewModel: SharedViewModel


    private fun setupRecyclerView() {
        sharedViewModel.selectedData2.observe(viewLifecycleOwner, Observer { data ->
            employeeAdapter = EmployeeAdapter(false,requireContext(),data!!,object : EmployeeAdapter.OnCLick
            {
                override fun onClick(data: CountryListModel.Data)
                {
                    val fragmentManager: FragmentManager = parentFragmentManager
                    val detailsFragment = fragmentManager.findFragmentByTag("DetailsFragment") as? DetailsFragment
                    if (detailsFragment != null)
                    {
                        detailsFragment.updateData(requireContext(), data)
                    } else {
                        Toast.makeText(requireContext(), "DetailsFragment not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onDoubleClick(
                    position: Int,
                    employee: CountryListModel.Data,
                    isSelected: Boolean
                ) {
                    sharedViewModel.updateSelection(position, isSelected)

                }


            })
            binding.recyclerView2.adapter = employeeAdapter

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        setupRecyclerView()

    }

    fun showToast(msg:String)
    {
        Toast.makeText(requireContext(), "$msg", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScreen2StateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

