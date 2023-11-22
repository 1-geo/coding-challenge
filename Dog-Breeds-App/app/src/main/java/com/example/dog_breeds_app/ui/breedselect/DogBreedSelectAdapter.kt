package com.example.dog_breeds_app.ui.breedselect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_breeds_app.databinding.DogBreedsSelectItemBinding

class DogBreedSelectAdapter(val breeds: List<String>, val listener: ItemClickListener): RecyclerView.Adapter<DogBreedSelectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DogBreedsSelectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(breeds[position])
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    fun handleClick(name: String) {
        listener.clickedBreed(name)
    }

    inner class ViewHolder(val binding: DogBreedsSelectItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.dogBreedName.text = name
            binding.dogBreedName.setOnClickListener {
                handleClick(name)
            }
        }
    }

    interface ItemClickListener {
        fun clickedBreed(name: String)
    }
}