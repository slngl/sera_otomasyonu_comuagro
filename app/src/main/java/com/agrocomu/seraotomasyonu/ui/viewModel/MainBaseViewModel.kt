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
import kotlinx.coroutines.Dispatchers
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

    init {
        createTabMenuItems()
    }

    fun startPolling() {
        val tickerChannel = ticker(10_000, 0) // Every 10 second
        viewModelScope.launch(Dispatchers.IO) {
            for (event in tickerChannel) {
                getControlPanelList()
            }
        }
    }

    fun getControlPanelList(){
        val returnData = mutableListOf<ControlPanelAdapterItem>()

        val msg1 = BluetoothControl.btRead()
        //soil humiduty
        returnData.add(
            ControlPanelAdapterItem(
                "Toprak Nemi",
                "Serada toprağın nem oranını gözterir, su ihtiyacı hakkında fikir verir.",
                null,
                null,
                msg1,
                msg1?.toDouble(),
                R.drawable.ic_humidity,
                ControlPanelAdapterItemType.READ_DATA
            )
        )

        val msg2 = BluetoothControl.btRead()
        //ambient humiduty
        returnData.add(
            ControlPanelAdapterItem(
                "Ortam Nemi",
                "Serada ortamın nem oranını gözterir, su ihtiyacı hakkında fikir verir.",
                null,
                null,
                "msg2",
                msg2?.toDouble(),
                R.drawable.ic_humidity,
                ControlPanelAdapterItemType.READ_DATA
            )
        )

        val msg3 = BluetoothControl.btRead()
        //temp
        returnData.add(
            ControlPanelAdapterItem(
                "Sıcaklık",
                "Sera sıcaklığını gösterir.",
                null,
                null,
                "msg1",
                msg3?.toDouble(),
                null,
                ControlPanelAdapterItemType.READ_DATA
            )
        )

        val msg4 = BluetoothControl.btRead()
        //wind rotate
        returnData.add(
            ControlPanelAdapterItem(
                "Rüzgar Yönü",
                "Rüzgar yönünü gösterir.",
                null,
                null,
                "msg4",
                msg4?.toDouble(),
                R.drawable.ic_baseline_toys_24,
                ControlPanelAdapterItemType.READ_DATA
            )
        )

        val msg5 = BluetoothControl.btRead()
        //wind speed
        returnData.add(
            ControlPanelAdapterItem(
                "Rüzgar Hızı",
                "Rüzgar hızını gösterir.",
                null,
                null,
                "msg1",
                msg5?.toDouble(),
                R.drawable.ic_baseline_toys_24,
                ControlPanelAdapterItemType.READ_DATA
            )
        )

        val msg6 = BluetoothControl.btRead()
        //water engine
        returnData.add(
            ControlPanelAdapterItem(
                "Su motoru",
                "Seranın sulama durumunu gösterir.",
                "Açık",
                null,
                "msg1",
                msg6?.toDouble(),
                R.drawable.ic_baseline_toys_24,
                ControlPanelAdapterItemType.SEND_DATA
            )
        )

        val msg7 = BluetoothControl.btRead()
        //first roof
        returnData.add(
            ControlPanelAdapterItem(
                "Çatı 1",
                "Bir numaralı çatının havalandırma için açık veya olduğunu gösterir. Çatı durumunu buradan kontrol edebilirsiniz.",
                "Açık",
                null,
                msg7,
                null,
                R.drawable.ic_broken_roof,
                ControlPanelAdapterItemType.SEND_DATA
            )
        )

        val msg8 = BluetoothControl.btRead()
        //second roof
        returnData.add(
            ControlPanelAdapterItem(
                "Çatı 2",
                "İki numaralı çatının havalandırma için açık veya olduğunu gösterir. Çatı durumunu buradan kontrol edebilirsiniz.",
                "Açık",
                null,
                msg8,
                null,
                R.drawable.ic_broken_roof,
                ControlPanelAdapterItemType.SEND_DATA
            )
        )
        val msg9 = BluetoothControl.btRead()
        //second roof
        returnData.add(
            ControlPanelAdapterItem(
                "Fan",
                "Havalandırmanın açık veya olduğunu gösterir. Fan durumunu buradan kontrol edebilirsiniz.",
                "Açık",
                null,
                msg9,
                null,
                R.drawable.ic_baseline_toys_24,
                ControlPanelAdapterItemType.SEND_DATA
            )
        )

        liveControlPanelData.postValue(returnData)
    }

    fun readBluetoothData() {
/*        val msg = BluetoothControl.btRead()
        val splited = msg?.split(":")

        when (splited?.firstOrNull { !it.isEmpty() }) {
            "ortamNem" -> liveAmbientHumidity.postValue(splited.last())
            "toprakNem" -> liveSoilHumidity.postValue(splited.last())
            "sicaklik" -> liveTemp.postValue(splited.last())
            "ruzgarYonu" -> liveWindRotate.postValue(splited.last())
            "ruzgarHizi" -> liveWindSpeed.postValue(splited.last())
            "catiBir" -> liveFirstRoofState.postValue(splited.last())
            "catiIki" -> liveSecondRoofState.postValue(splited.last())
            "suMotoru" -> liveWaterPump.postValue(splited.last())
            "fan" -> liveFan.postValue(splited.last())
        }*/
        val msg = BluetoothControl.btRead()
        liveAmbientHumidity.postValue(msg)
        liveSoilHumidity.postValue(msg)
        liveTemp.postValue(msg)
        liveWindRotate.postValue(msg)
        liveWindSpeed.postValue(msg)
        liveFirstRoofState.postValue(msg)
        liveSecondRoofState.postValue(msg)
        liveWaterPump.postValue(msg)
        liveFan.postValue(msg)
    }


    fun sendDataToBluetooth(msg: String) {
        BluetoothControl.btWrite(msg)
    }

    fun closeFirstRoof() {
        BluetoothControl.btWrite("b")
    }

    fun openFirstRoof() {
        BluetoothControl.btWrite("v")
    }

    fun closeSecondRoof() {
        BluetoothControl.btWrite("n")
    }

    fun openSecondRoof() {
        BluetoothControl.btWrite("m")
    }

    fun createTabMenuItems() {
        val menuList = mutableListOf<DashboardMenuItemEntity>()
        menuList.add(
            DashboardMenuItemEntity(
                0,
                R.string.dashboard_home,
                DashboardMenuItemType.home
            )
        )
        menuList.add(
            DashboardMenuItemEntity(
                1,
                R.string.dashboard_weather,
                DashboardMenuItemType.weather
            )
        )
        menuList.add(
            DashboardMenuItemEntity(
                2,
                R.string.dashboard_control_panel,
                DashboardMenuItemType.controlpanel
            )
        )
        menuList.add(
            DashboardMenuItemEntity(
                3,
                R.string.dashboard_settings,
                DashboardMenuItemType.settings
            )
        )
        liveDataMenuItems.postValue(menuList)
    }
}