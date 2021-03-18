package com.agrocomu.seraotomasyonu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentWeatherBinding
import com.agrocomu.seraotomasyonu.ui.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_weather

    private val viewModel : WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getWeather("çanakkale")

        val citys = arrayOf(
            "Adana", "Adiyaman", "Afyonkarahisar", "Agri",
            "Amasya", "Ankara", "Antalya", "Artvin", "Aydin", "Balikesir",
            "Bilecik", "Bingol", "Bitlis", "Bolu", "Burdur", "Bursa",
            "Çanakkale", "Çankiri", "Çorum", "Denizli", "Diyarbakir",
            "Edirne", "Elazig", "Erzincan", "Erzurum", "Eskisehir",
            "Gaziantep", "Giresun", "Gumushane", "Hakkari", "Hatay",
            "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu",
            "Kayseri", "Kirklareli", "Kirsehir", "Kocaeli", "Konya",
            "Kutahya", "Malatya", "Manisa", "Kahramanmaras", "Mardin",
            "Mugla", "Mus", "Nevsehir", "Nigde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdag", "Tokat",
            "Trabzon", "Tunceli", "Sanliurfa", "Usak", "Van", "Yozgat",
            "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kirikkale",
            "Batman", "sirnak", "Bartin", "Ardahan", "Igdir", "Yalova",
            "Karabuk", "Kilis", "Osmaniye", "Duzce"
        )

        binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                // Here you change your value or do whatever you want
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Here comes when you didnt choose anything from your spinner logic
            }
        })

        return binding.root
    }

}