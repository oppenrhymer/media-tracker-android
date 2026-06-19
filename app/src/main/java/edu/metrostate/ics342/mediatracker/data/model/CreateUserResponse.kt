package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val id: String,
    val email: String,
    val username: String,
    val displayName: String,
    val bio: String? = null,
    val avatarUrl: String? = null,
    val followerCount: Int? = null,
    val followingCount: Int? = null,
    val trackedCount: Int? = null,
    val isFollowing: Boolean? = null,
    val createdAt: String? = null
)
