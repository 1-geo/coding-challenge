package com.example.dog_breeds_app.ui.breedview

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dog_breeds_app.databinding.DogBreedInfoScreenBinding
import com.example.dog_breeds_app.utils.Constants
import com.example.myapplication.base.BaseActivity

class DogBreedInfoScreen: BaseActivity<DogBreedInfoScreenPresenter>() {

    lateinit var binding: DogBreedInfoScreenBinding

    private val listener = object: DogBreedInfoScreenPresenter.ViewListener {
        override fun loadImages(imageUrls: List<String>) {
            binding.dogBreedInfoList.apply {
                adapter = DogBreedImageAdapter(imageUrls)
                layoutManager = GridLayoutManager(this@DogBreedInfoScreen, 2)
            }
        }

        override fun showErrorMessage(error: String) {
            Toast.makeText(this@DogBreedInfoScreen, error, Toast.LENGTH_LONG).show()
        }

        override fun showProgress() {
            this@DogBreedInfoScreen.showProgress()
        }

        override fun hideProgress() {
            this@DogBreedInfoScreen.cancelProgress()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DogBreedInfoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPresenter().loadImageForBreed(intent.getStringExtra(Constants.BUNDLE_BREED_NAME_KEY), listener)
    }


    override fun initPresenter(): DogBreedInfoScreenPresenter {
        return DogBreedInfoScreenPresenter()
    }
}