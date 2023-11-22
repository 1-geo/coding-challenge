package com.example.a20180101_mm_nycschools.api

import com.example.dog_breeds_app.model.DogBreed
import com.example.dog_breeds_app.model.DogBreedImage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/breeds/list/all")
    fun retrieveBreeds(): Observable<DogBreed>

    @GET("api/breed/{breed}/images")
    fun retrieveBreedImages(@Path("breed") name: String): Observable<DogBreedImage>
}