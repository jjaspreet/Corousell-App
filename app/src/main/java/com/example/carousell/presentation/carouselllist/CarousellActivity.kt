package com.example.carousell.presentation.carouselllist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carousell.R
import com.example.carousell.common.toToast
import com.example.carousell.data.remote.dto.CarousellDto
import com.example.carousell.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarousellActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

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
                        binding.progressLayout.visibility = VISIBLE
                    }

                    is CarousellUIState.Success -> {
                        binding.progressLayout.visibility = GONE
                        it.data.let { carouselList ->
                            adapter.submitList(carouselList)
                        }
                    }

                    is CarousellUIState.Error -> {
                        binding.progressLayout.visibility = GONE
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
                viewModel.fetchResponseByRank()
                true
            }

            R.id.recent_menu -> {
                viewModel.fetchResponseByTime()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(item: CarousellDto) {
        (item.rank).toString().toToast(this).show()
    }
}