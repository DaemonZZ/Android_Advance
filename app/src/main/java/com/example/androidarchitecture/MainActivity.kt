package com.example.androidarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.adapter.CountryAdapter
import com.example.androidarchitecture.databinding.ActivityMainBinding
import com.example.androidarchitecture.internet.DataPuller
import com.example.androidarchitecture.viewmodel.CountryViewModel
import kotlinx.coroutines.*

const val BASE_URL = "https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json"

class MainActivity : AppCompatActivity() {
    private val job = Job()
    private val mainScope = CoroutineScope(Dispatchers.IO + job)
    private lateinit var model: CountryViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rvCountry: RecyclerView = binding.rvCountry
        rvCountry.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainScope.launch {
            model = ViewModelProvider(this@MainActivity).get(CountryViewModel::class.java)
            val dataCountries = model.getCountry()
            withContext(Dispatchers.Main){
                dataCountries.observe(this@MainActivity, Observer {
                    rvCountry.adapter = CountryAdapter(it)
                })
            }
        }

    }
}