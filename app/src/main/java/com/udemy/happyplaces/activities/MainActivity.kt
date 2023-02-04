package com.udemy.happyplaces.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    private fun getHappyPlacesListFromLocalDatabase() {
        val databaseHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = databaseHandler.getHappyPlaceList()

        if (getHappyPlaceList.size > 0) {
            for (i in getHappyPlaceList) {
                Log.e("Title", i.title)
                Log.e("Description", i.description)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}