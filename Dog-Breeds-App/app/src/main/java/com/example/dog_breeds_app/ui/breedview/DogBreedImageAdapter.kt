package com.example.dog_breeds_app.ui.breedview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_breeds_app.databinding.DogBreedInfoItemBinding
import com.squareup.picasso.Picasso

class DogBreedImageAdapter(val images: List<String>): RecyclerView.Adapter<DogBreedImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DogBreedInfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(val binding: DogBreedInfoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            Picasso.get().load(url).into(binding.dogBreedImage)
        }
    }
}