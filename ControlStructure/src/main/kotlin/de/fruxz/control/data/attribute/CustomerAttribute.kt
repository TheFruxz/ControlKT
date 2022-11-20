package de.fruxz.control.data.attribute

import kotlinx.serialization.Serializable

@Serializable
data class CustomerAttribute(
	val attributeId: String,
	val attributeName: String,
	val attributeValue: String,
	val attributeType: AttributeType,
)
