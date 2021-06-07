package com.agrocomu.seraotomasyonu.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BluetoothControl
import com.agrocomu.seraotomasyonu.entity.ControlPanelAdapterItem
import com.agrocomu.seraotomasyonu.entity.ControlPanelAdapterItemType
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemEntity
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainBaseViewModel @Inject constructor() : ViewModel() {

    //send data states
    val liveFirstRoofState = MutableLiveData<String?>()
    val liveSecondRoofState = MutableLiveData<String?>()
    val liveWaterPump = MutableLiveData<String?>()
    val liveFan = MutableLiveData<String?>()

    //read data
    val liveWindSpeed = MutableLiveData<String?>()
    val liveWindRotate = MutableLiveData<String?>()
    val liveTemp = MutableLiveData<String?>()
    val liveAmbientHumidity = MutableLiveData<String?>()
    val liveSoilHumidity = MutableLiveData<String?>()
    val liveDataMenuItems = MutableLiveData<List<DashboardMenuItemEntity>>()
    val liveControlPanelData = MutableLiveData<List<ControlPanelAdapterItem>>()

    init{
        createTabMenuItems()
    }

    fun startPolling() {
        val tickerChannel = ticker(10_000,0) // Every 10 second
        viewModelScope.launch {
            for (event in tickerChannel){
                getControlPanelList()
            }
        }
    }

    fun getControlPanelList(){
        val returnData = mutableListOf<ControlPanelAdapterItem>()

        for (i in 0..9){
            readBluetoothData()


        }
        liveSoilHumidity.value?.let {
        //soil humiduty
            returnData.add(
                ControlPanelAdapterItem(
                    "Toprak Nemi",
                    "Serada toprağın nem oranını gözterir, su ihtiyacı hakkında fikir verir.",
                    null,
                    null,
                    it,
                    0.1,
                    R.drawable.ic_humidity,
                    ControlPanelAdapterItemType.READ_DATA
                )
            )
        }

        liveAmbientHumidity.value?.let {
        //ambient humiduty
            returnData.add(
                ControlPanelAdapterItem(
                    "Ortam Nemi",
                    "Serada ortamın nem oranını gözterir, su ihtiyacı hakkında fikir verir.",
                    null,
                    null,
                    it,
                    0.2,
                    R.drawable.ic_humidity,
                    ControlPanelAdapterItemType.READ_DATA
                )
            )
        }

        liveTemp.value?.let {
            //temp
            returnData.add(
                ControlPanelAdapterItem(
                    "Sıcaklık",
                    "Sera sıcaklığını gösterir.",
                    null,
                    null,
                    it,
                    0.3,
                    null,
                    ControlPanelAdapterItemType.READ_DATA
                )
            )
        }

    liveWindRotate.value?.let {
        //wind rotate
        returnData.add(
            ControlPanelAdapterItem(
                "Rüzgar Yönü",
                "Rüzgar yönünü gösterir.",
                null,
                null,
                it,
                0.4,
                R.drawable.ic_baseline_toys_24,
                ControlPanelAdapterItemType.READ_DATA
            )
        )
    }

        liveWindSpeed.value?.let {
            //wind speed
            returnData.add(
                ControlPanelAdapterItem(
                    "Rüzgar Hızı",
                    "Rüzgar hızını gösterir.",
                    null,
                    null,
                    it,
                    0.5,
                    R.drawable.ic_baseline_toys_24,
                    ControlPanelAdapterItemType.READ_DATA
                )
            )
        }

        liveWaterPump.value?.let {
            //water engine
            returnData.add(
                ControlPanelAdapterItem(
                    "Su motoru",
                    "Seranın sulama durumunu gösterir.",
                    it,
                    null,
                    it,
                    0.6,
                    R.drawable.ic_baseline_toys_24,
                    ControlPanelAdapterItemType.SEND_DATA
                )
            )
        }

        liveFirstRoofState.value?.let {
            //first roof
            returnData.add(
                ControlPanelAdapterItem(
                    "Çatı 1",
                    "Bir numaralı çatının havalandırma için açık veya olduğunu gösterir. Çatı durumunu buradan kontrol edebilirsiniz.",
                    it,
                    "v",
                    null,
                    0.7,
                    R.drawable.ic_broken_roof,
                    ControlPanelAdapterItemType.SEND_DATA
                )
            )
        }

        liveSecondRoofState.value?.let {
            //second roof
            returnData.add(
                ControlPanelAdapterItem(
                    "Çatı 2",
                    "İki numaralı çatının havalandırma için açık veya olduğunu gösterir. Çatı durumunu buradan kontrol edebilirsiniz.",
                    it,
                    null,
                    "v",
                    null,
                    R.drawable.ic_broken_roof,
                    ControlPanelAdapterItemType.SEND_DATA
                )
            )
        }

        liveFan.value?.let {
            //fan
            returnData.add(
                ControlPanelAdapterItem(
                    "Fan",
                    "İki numaralı çatının havalandırma için açık veya olduğunu gösterir. Çatı durumunu buradan kontrol edebilirsiniz.",
                    it,
                    null,
                    "v",
                    null,
                    R.drawable.ic_broken_roof,
                    ControlPanelAdapterItemType.SEND_DATA
                )
            )
        }

        liveControlPanelData.postValue(returnData)
    }

    fun readBluetoothData(){
        val msg = BluetoothControl.btRead()
        msg?.let {  msg->
            when(msg){
                "ortamNem" -> liveAmbientHumidity.postValue(msg)
                "toprakNem" -> liveSoilHumidity.postValue(msg)
                "sicaklik" -> liveTemp.postValue(msg)
                "ruzgarYonu" -> liveWindRotate.postValue(msg)
                "ruzgarHizi" -> liveWindSpeed.postValue(msg)
                "catiBir" -> liveFirstRoofState.postValue(msg)
                "catiIki" -> liveSecondRoofState.postValue(msg)
                "suMotoru" -> liveWaterPump.postValue(msg)
                "fan" -> liveFan.postValue(msg)
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