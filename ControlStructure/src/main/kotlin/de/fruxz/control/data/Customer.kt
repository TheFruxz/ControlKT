package de.fruxz.control.data

import de.fruxz.control.data.attribute.CustomerAttribute
import kotlinx.serialization.Serializable

@Serializable
data class Customer(
	val id: String,
	val attributes: List<CustomerAttribute>,
)
