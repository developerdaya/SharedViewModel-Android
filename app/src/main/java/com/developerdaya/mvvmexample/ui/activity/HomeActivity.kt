package com.developerdaya.mvvmexample.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.mvvmexample.R
import com.developerdaya.mvvmexample.databinding.ActivityEmployeesBinding
import com.developerdaya.mvvmexample.model.CountryListModel
import com.developerdaya.mvvmexample.ui.fragment.DetailsFragment
import com.developerdaya.mvvmexample.ui.fragment.Screen1StateList
import com.developerdaya.mvvmexample.ui.fragment.Screen2StateList
import com.developerdaya.mvvmexample.ui.viewmodel.EmployeesViewModel
import com.developerdaya.mvvmexample.ui.viewmodel.SharedViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmployeesBinding
    lateinit var viewModel: EmployeesViewModel
    lateinit var sharedViewModel: SharedViewModel

    var listData = ArrayList<CountryListModel.Data>()
    // Reference to the Screen2StateList fragment
    private lateinit var screen2StateListFragment: Screen2StateList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        initViews()
        observer()
        loadFragment1(Screen1StateList())
        loadFragment2(Screen2StateList()) // Load and store reference to Screen2StateList fragment
        loadFragment3(DetailsFragment())

    }

    private fun observer() {
        viewModel.getCountryResp.observe(this) {
            sharedViewModel.select(it.data)
            listData = it.data
        }
    }
     fun initViews()
     {
        viewModel = ViewModelProvider(this).get(EmployeesViewModel::class.java)
        viewModel.getEmployees()
        binding.etSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                    val myNewList = listData.filter { it.state.contains(s.toString()) }
                    sharedViewModel.updateNewList(myNewList as ArrayList<CountryListModel.Data>)

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun showToast(msg:String)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // Method to load the fragment and save its reference
    private fun loadFragment2(fragment: Screen2StateList) {
        screen2StateListFragment = fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFragment2, fragment)
            .commit()
    }

    private fun loadFragment1(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFragment1, fragment)
            .commit()
    }

    private fun loadFragment3(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFragment3, fragment, "DetailsFragment")
            .commit()
    }
}

