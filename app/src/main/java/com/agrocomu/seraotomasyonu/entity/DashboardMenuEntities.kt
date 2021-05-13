package com.agrocomu.seraotomasyonu.entity

data class DashboardMenuItemEntity(
    val position: Int,
    val title: Int,
    val key: DashboardMenuItemType
)

enum class DashboardMenuItemType {
    home, weather, settings, controlpanel
}
