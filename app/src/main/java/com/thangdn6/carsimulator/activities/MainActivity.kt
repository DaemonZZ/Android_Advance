package com.thangdn6.carsimulator.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.activity.viewModels
import com.example.carsimulator.databinding.ActivityMainBinding
import com.thangdn6.carsimulator.model.Tire
import com.thangdn6.carsimulator.service.AIDLCarService
import com.thangdn6.carsimulator.viewmodel.CarTiresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    companion object{
        private const val TAG = "ThangDN6 - MainActivity"
    }
    @Inject
    lateinit var scope:CoroutineScope
    private lateinit var binding:ActivityMainBinding
    private var listTires = listOf<Tire>()
     val viewModel by viewModels<CarTiresViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()

    }

    private fun observer() {
        viewModel.carTires.observe(this,{
            listTires = it
            loadData()
            if(listTires.isNotEmpty()){
                startAIDLService()
            }
        })
    }

    private fun loadData() {
        binding.seekPress1.apply {
            max=25
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekPress2.apply {
            max=25
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekPress3.apply {
            max=25
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekPress4.apply {
            max=25
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekTemp1.apply {
            max=200
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekTemp2.apply {
            max=200
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekTemp3.apply {
            max=200
            setOnSeekBarChangeListener(this@MainActivity)
        }
        binding.seekTemp4.apply {
            max=200
            setOnSeekBarChangeListener(this@MainActivity)
        }


        binding.tire1 = listTires[0]
        binding.tire2 = listTires[1]
        binding.tire3 = listTires[2]
        binding.tire4 = listTires[3]


    }

    private fun startAIDLService() {
        val intent = Intent(this, AIDLCarService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        Log.d(TAG, "onStopTrackingTouch: ")
        when(seekBar?.id){
            binding.seekPress1.id -> {
                listTires[0].setPressSeekBarValue(seekBar.progress)
                commitUpdate(0)
            }
            binding.seekPress2.id -> {
                listTires[1].setPressSeekBarValue(seekBar.progress)
                commitUpdate(1)
            }
            binding.seekPress3.id -> {
                listTires[2].setPressSeekBarValue(seekBar.progress)
                commitUpdate(2)
            }
            binding.seekPress4.id -> {
                listTires[3].setPressSeekBarValue(seekBar.progress)
                commitUpdate(3)
            }
            binding.seekTemp1.id -> {
                listTires[0].setTempSeekBarValue(seekBar.progress)
                commitUpdate(0)
            }
            binding.seekTemp2.id -> {
                listTires[1].setTempSeekBarValue(seekBar.progress)
                commitUpdate(1)
            }
            binding.seekTemp3.id -> {
                listTires[2].setTempSeekBarValue(seekBar.progress)
                commitUpdate(2)
            }
            binding.seekTemp4.id -> {
                listTires[3].setTempSeekBarValue(seekBar.progress)
                commitUpdate(3)
            }

        }
    }

    private fun commitUpdate(index:Int) {
        scope.launch {
            viewModel.updateTire(listTires[index])
        }
        viewModel.carTires.postValue(listTires)
    }
}