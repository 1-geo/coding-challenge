package com.example.a20180101_mm_nycschools.api

import com.example.dog_breeds_app.model.DogBreed
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object Client {
    private var mClient: OkHttpClient? = null
    private var mGsonConverter: GsonConverterFactory? = null

    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                mClient = httpBuilder.build()

            }
            return mClient!!
        }


    val gsonConverter: GsonConverterFactory
        get() {
            if(mGsonConverter == null){
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .registerTypeAdapter(DogBreed::class.java, DogBreedDeserializer)
                            .setLenient()
                            .disableHtmlEscaping()
                            .create())
            }
            return mGsonConverter!!
        }


    /**
     * Deserializer for getting breeds
     */
    object DogBreedDeserializer: JsonDeserializer<DogBreed> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DogBreed {
            if (json == null || context == null) {
                // handle error here
                throw Exception("Error")
            }
            val obj = json.asJsonObject

            // create breeds list
            val breeds = obj.get("message").asJsonObject.entrySet()
            val breedsList = breeds.map {
                it.key
            }

            return DogBreed(breedsList)
        }
    }
}