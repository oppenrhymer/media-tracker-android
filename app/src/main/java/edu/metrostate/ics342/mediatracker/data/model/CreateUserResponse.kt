package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val id: String,
    val email: String,
    val username: String,
    val displayName: String,
    val bio: String,
    val avatarUrl: String,
    val followerCount: Int,
    val followingCount: Int,
    val trackedCount: Int,
    val isFollowing: Boolean,
    val createdAt: String
)
