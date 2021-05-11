package com.agrocomu.seraotomasyonu.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrocomu.seraotomasyonu.base.BluetoothControl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    val liveFirstRoofState = MutableLiveData<String?>()
    val liveSecondRoofState = MutableLiveData<String>()
    val liveWindSpeed = MutableLiveData<String>()
    val liveWindRotate = MutableLiveData<String>()
    val liveTemp = MutableLiveData<String>()
    val liveAmbientHumidity = MutableLiveData<String?>()
    val liveSoilHumidity = MutableLiveData<String>()

    fun readBluetoothData(){
        val msg = BluetoothControl.btRead()
        msg?.let {  msg->
            when(msg){
                "a" -> liveAmbientHumidity.postValue(msg)
                "b" -> liveSoilHumidity.postValue(msg)
                "c" -> liveTemp.postValue(msg)
                "d" -> liveWindRotate.postValue(msg)
                "e" -> liveWindSpeed.postValue(msg)

            }
        }

    }

    fun sendDataToBluetooth(msg : String){
        BluetoothControl.btWrite(msg)
    }

    fun closeFirstRoof(){
        BluetoothControl.btWrite("b")
    }

    fun openFirstRoof(){
        BluetoothControl.btWrite("v")
    }

    fun closeSecondRoof(){
        BluetoothControl.btWrite("n")
    }

    fun openSecondRoof(){
        BluetoothControl.btWrite("m")
    }
}