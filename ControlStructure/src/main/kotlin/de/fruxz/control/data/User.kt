package de.fruxz.control.data

import java.util.UUID

data class User(
	val userId: UUID,
	val permissions: Set<String>,
)
