package edu.metrostate.ics342.mediatracker.data

import android.util.Log
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.network.ApiConstants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class MediaResultsRepository(
) {

    /*val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }
        .build()
    private val api: ApiService = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(
            Json.asConverterFactory(
                contentType="application/json; charset=utf-8".toMediaType()))
        .build()
        .create(ApiService::class.java)*/


    suspend fun searchMedia(
        query: String,
        accessToken: String?
    ) : List<Media> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
        val api: ApiService = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                Json.asConverterFactory(
                    contentType="application/json; charset=utf-8".toMediaType()))
            .build()
            .create(ApiService::class.java)

        val results = api.media()
        Log.d("MediaRepo","Results: " + results.get(0))
        //val createUserResponse = api.createUser(createUserRequest)
        //Log.d("UserRepository", "createUserResponse: $createUserResponse");
        return results
    }



}