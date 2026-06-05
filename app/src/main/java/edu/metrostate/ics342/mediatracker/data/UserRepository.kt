package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.CreateUserRequest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

const val baseURL = "https://wjtzkgpxmxtzcczzbvrz.supabase.co/functions/v1"
class UserRepository {
    private val api: ApiService = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(
                Json.asConverterFactory(
                    contentType="application/json; charset=utf-8".toMediaType()))
            .build()
            .create(ApiService::class.java)


    suspend fun createAccount(
        displayName: String,
        userName: String,
        email: String,
        password: String
    ) {
        val createUserRequest = CreateUserRequest(
            email = email,
            displayName = displayName,
            username = userName,
            password = password,
            clientId = "",
            clientSecret = ""
        )
        api.createUser(createUserRequest)
    }
}