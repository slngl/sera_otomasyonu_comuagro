package com.agrocomu.seraotomasyonu.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BluetoothControl
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemEntity
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainBaseViewModel @Inject constructor() : ViewModel() {

    val liveFirstRoofState = MutableLiveData<String?>()
    val liveSecondRoofState = MutableLiveData<String>()
    val liveWindSpeed = MutableLiveData<String>()
    val liveWindRotate = MutableLiveData<String>()
    val liveTemp = MutableLiveData<String>()
    val liveAmbientHumidity = MutableLiveData<String?>()
    val liveSoilHumidity = MutableLiveData<String>()
    val liveDataMenuItems = MutableLiveData<List<DashboardMenuItemEntity>>()

    init{
        createTabMenuItems()
    }

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

    fun createTabMenuItems() {
        val menuList = mutableListOf<DashboardMenuItemEntity>()
        menuList.add(DashboardMenuItemEntity(0, R.string.dashboard_home, DashboardMenuItemType.home))
        menuList.add(DashboardMenuItemEntity(1, R.string.dashboard_weather, DashboardMenuItemType.weather))
        menuList.add(DashboardMenuItemEntity(2, R.string.dashboard_control_panel, DashboardMenuItemType.controlpanel))
        menuList.add(DashboardMenuItemEntity(3, R.string.dashboard_settings, DashboardMenuItemType.settings))
        liveDataMenuItems.postValue(menuList)
    }
}