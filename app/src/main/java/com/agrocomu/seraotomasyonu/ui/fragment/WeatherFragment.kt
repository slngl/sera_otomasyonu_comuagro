package com.agrocomu.seraotomasyonu.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentWeatherBinding
import com.agrocomu.seraotomasyonu.ui.viewModel.WeatherViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_weather.*

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

        viewModel.getWeather("Çanakkale")

        val citys = arrayOf(" ",
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


        val adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, citys)
        binding.spinner.adapter = adapter


        viewModel.liveWeatherResponse.observe(viewLifecycleOwner, Observer {
            binding.tvRuzgarHizi.text = "Rüzgar hızı: ${it.wind?.speed.toString()} m/s"
            binding.tvSicaklik.text = "   ${it.main?.temp} °C"
            binding.tvAciklama.text = it.weather?.get(0)?.description ?: "Bağlantı Hatası"
            Glide.with(this).load("http://openweathermap.org/img/wn/${it.weather?.get(0)?.icon}@2x.png")
                .into(binding.icWeather)
        })

/*        viewModel.liveSpeed.observe(viewLifecycleOwner, Observer {
            binding.tvRuzgarHizi.text = it
        })
        viewModel.liveDesc.observe(viewLifecycleOwner, Observer {
            binding.tvAciklama.text = it
        })*/

        binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                // Here you change your value or do whatever you want
                val city = citys[position]
                viewModel.getWeather(city)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Here comes when you didnt choose anything from your spinner logic
            }
        })

        return binding.root
    }

}