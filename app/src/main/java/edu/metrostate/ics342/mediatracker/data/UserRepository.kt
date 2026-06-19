package edu.metrostate.ics342.mediatracker.data

import android.util.Log
import edu.metrostate.ics342.mediatracker.data.model.CreateUserRequest
import edu.metrostate.ics342.mediatracker.data.model.TokenRequest
import edu.metrostate.ics342.mediatracker.data.model.TokenResponse
import edu.metrostate.ics342.mediatracker.data.network.ApiConstants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


class UserRepository {
    private val api: ApiService = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
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
        Log.d("UserRepository","createAccount called");
        Log.d("UserRepository","Params: $email, $displayName, $userName, $password")
        val createUserRequest = CreateUserRequest(
            email = email,
            displayName = displayName,
            username = userName,
            password = password,
            clientId = ApiConstants.CLIENT_ID,
            clientSecret = ApiConstants.CLIENT_SECRET
        )
        Log.d("UserRepository", "as json" + Json.encodeToString(createUserRequest))
        //val createUserResponse = api.createUser(createUserRequest)
        //Log.d("UserRepository", "createUserResponse: $createUserResponse");
    }

    suspend fun login(
        email: String,
        password: String
    ): TokenResponse {
        Log.d("UserRepository", "getToken called")
        Log.d("UserRepository","Params: $email, $password")
        val tokenRequest = TokenRequest(
            grantType = "password",
            email = email,
            password = password,
            clientId = ApiConstants.CLIENT_ID,
            clientSecret = ApiConstants.CLIENT_SECRET
        )
        var tokenResponse = TokenResponse()
        try {
            tokenResponse = api.login(tokenRequest)
        } catch (e: HttpException) {
            Log.e("UserRepository","Error logging in. HttpException caught...")

        }
        Log.d("UserRepository", "tokenResponse: $ tokenResponse")

        return tokenResponse
    }

}