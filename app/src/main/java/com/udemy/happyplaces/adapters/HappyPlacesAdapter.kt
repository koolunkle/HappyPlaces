package com.udemy.happyplaces.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udemy.happyplaces.databinding.ItemHappyPlaceBinding
import com.udemy.happyplaces.models.HappyPlaceModel

class HappyPlacesAdapter(
    private val list: ArrayList<HappyPlaceModel>
) : RecyclerView.Adapter<HappyPlacesAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHappyPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPlaceImage = binding.ivPlaceImage
        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHappyPlaceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.ivPlaceImage.setImageURI(Uri.parse(model.image))
        holder.tvTitle.text = model.title
        holder.tvDescription.text = model.description
    }

    override fun getItemCount() = list.size
}