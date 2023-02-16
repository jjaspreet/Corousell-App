package com.example.myapplication.presentation.carouselllist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.toToast
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarousellActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var carouselList: MutableList<CarousellDto> = mutableListOf()

    private val viewModel: CorousellViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = CarousellAdapter(this, this)

        binding.apply {
            recyclerView.adapter = adapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.carouselResponse.collect {
                when (it) {

                    is CarousellUIState.Loading -> {
                        binding.progressLayout.visibility = View.VISIBLE
                    }

                    is CarousellUIState.Success -> {

                        binding.progressLayout.visibility = View.GONE
                        it.data.let { carouselList ->

                            adapter.submitList(carouselList)
                            this@CarousellActivity.carouselList.clear()
                            this@CarousellActivity.carouselList = carouselList.toMutableList()
                        }
                    }

                    is CarousellUIState.Error -> {
                        binding.progressLayout.visibility = View.GONE
                    }

                    else -> {
                        //TODO: Respective handling , If needed
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu_style, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.popular_menu -> {
                viewModel.fetchResponseByRank(carouselList)
                true
            }

            R.id.recent_menu -> {
                viewModel.fetchResponseByTime(carouselList)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(item: CarousellDto) {
        item.title.toToast(this).show()
    }
}