package com.agrocomu.seraotomasyonu.entity

data class ControlPanelAdapterItem(
    val title: String? = null,
    val description: String? = null,
    val state: String? = null, //for SEND_DATA
    val sendMessage: String? = null,
    val readMessage: String? = null,
    val amount: Double? = null, //for READ_DATA
    val controlPanelAdapterItemType: ControlPanelAdapterItemType
)

enum class ControlPanelAdapterItemType{
    READ_DATA,
    SEND_DATA
}