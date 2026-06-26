package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.CreateUserRequest
import edu.metrostate.ics342.mediatracker.data.model.CreateUserResponse
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.MediaSearchResponse
import edu.metrostate.ics342.mediatracker.data.model.TokenRequest
import edu.metrostate.ics342.mediatracker.data.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    suspend fun createUser(@Body request: CreateUserRequest): CreateUserResponse

    @POST("tokens")
    suspend fun login(@Body request: TokenRequest): TokenResponse


    @GET("media")
    suspend fun media() : List<Media>
}