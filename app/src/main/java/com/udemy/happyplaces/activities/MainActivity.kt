package com.udemy.happyplaces.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.udemy.happyplaces.adapters.HappyPlacesAdapter
import com.udemy.happyplaces.database.DatabaseHandler
import com.udemy.happyplaces.databinding.ActivityMainBinding
import com.udemy.happyplaces.models.HappyPlaceModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlacesListFromLocalDatabase()
    }

    private fun setupHappyPlacesRecyclerView(happyPlaceList: ArrayList<HappyPlaceModel>) {
        binding.rvHappyPlacesList.layoutManager = LinearLayoutManager(this)
        binding.rvHappyPlacesList.setHasFixedSize(true)

        val placesAdapter = HappyPlacesAdapter(happyPlaceList)
        binding.rvHappyPlacesList.adapter = placesAdapter
    }

    private fun getHappyPlacesListFromLocalDatabase() {
        val databaseHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = databaseHandler.getHappyPlaceList()

        if (getHappyPlaceList.size > 0) {
            binding.rvHappyPlacesList.visibility = View.VISIBLE
            binding.tvNoRecordsAvailable.visibility = View.GONE
            setupHappyPlacesRecyclerView(getHappyPlaceList)
        } else {
            binding.rvHappyPlacesList.visibility = View.GONE
            binding.tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}