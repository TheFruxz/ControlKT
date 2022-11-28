package de.fruxz.control.data

import java.util.UUID

data class User(
	val userId: UUID,
	private val username: String,
	private val password: String,
	val permissions: Set<String>,
)
