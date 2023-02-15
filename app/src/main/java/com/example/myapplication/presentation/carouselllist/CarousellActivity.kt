package com.example.myapplication.presentation.carouselllist

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.common.toToast
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarousellActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private  var carouselList: MutableList<CarousellDto> = mutableListOf()

    private val viewModel: CorouselViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = CarousellAdapter(this)

        binding.button.setOnClickListener {
            viewModel.fetchResponseByRank(carouselList)
        }

        binding.apply {

            recyclerView.adapter = adapter
        }

        lifecycleScope.launchWhenStarted {

            viewModel.carouselResponse.collect {

                when (it) {

                    is CarouselUIState.Loading -> {
                     binding.progressLayout.visibility = View.VISIBLE
                    }

                    is CarouselUIState.Success -> {
                        binding.progressLayout.visibility = View.GONE
                        it.data.let { carouselList->

                            adapter.submitList(carouselList)
                            this@CarousellActivity.carouselList.clear()
                            this@CarousellActivity.carouselList = carouselList.toMutableList()
                        }
                    }

                    is CarouselUIState.Error -> {
                        binding.progressLayout.visibility = View.GONE
                    }

                    else -> {

                    }
                }
            }
        }

    }

    override fun onItemClick(photo: CarousellDto) {
        photo.title.toToast(this)
    }
}