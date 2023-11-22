package com.example.dog_breeds_app.ui.breedselect

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dog_breeds_app.databinding.DogBreedSelectScreenBinding
import com.example.dog_breeds_app.ui.breedview.DogBreedInfoScreen
import com.example.dog_breeds_app.utils.Constants
import com.example.myapplication.base.BaseActivity

class DogBreedSelectScreen: BaseActivity<DogBreedSelectPresenter>() {

    private lateinit var binding: DogBreedSelectScreenBinding

    private val listener = object: DogBreedSelectPresenter.ViewListener {
        override fun loadBreeds(breeds: List<String>) {
            val adapter = DogBreedSelectAdapter(breeds, object : DogBreedSelectAdapter.ItemClickListener {
                    override fun clickedBreed(name: String) {
                        val intent = Intent(this@DogBreedSelectScreen, DogBreedInfoScreen::class.java)
                        intent.putExtra(Constants.BUNDLE_BREED_NAME_KEY, name)
                        startActivity(intent)
                    }
                })
            binding.breedsList.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(this@DogBreedSelectScreen)
            }
        }

        override fun showError(errorMessage: String) {
            Toast.makeText(this@DogBreedSelectScreen, errorMessage, Toast.LENGTH_LONG).show()
        }

        override fun showProgress() = this@DogBreedSelectScreen.showProgress()
        override fun hideProgress() = this@DogBreedSelectScreen.cancelProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DogBreedSelectScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPresenter().getDogBreeds(listener)

    }

    override fun initPresenter(): DogBreedSelectPresenter {
        return DogBreedSelectPresenter()
    }
}