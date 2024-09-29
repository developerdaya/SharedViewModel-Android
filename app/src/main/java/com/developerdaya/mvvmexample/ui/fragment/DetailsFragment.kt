package com.developerdaya.mvvmexample.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.developerdaya.mvvmexample.R
import com.developerdaya.mvvmexample.databinding.FragmentDetailsBinding
import com.developerdaya.mvvmexample.model.CountryListModel

class DetailsFragment : Fragment() {

    var data: CountryListModel.Data? = null
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    fun updateData(context: Context, data: CountryListModel.Data) {
        this.data = data

        // Check if the view is already created (binding is not null)
        if (_binding != null) {
            // View is ready, update the UI immediately
            dataSetup(data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // If data was set before the view was created, update the UI now
        data?.let {
            dataSetup(it)
        }
    }

    private fun dataSetup(data: CountryListModel.Data) {
        binding.mStatenam2.text = data.state
        binding.mPopulation2.text = data.population.toString()
        binding.mCountry.text = data.counties.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
