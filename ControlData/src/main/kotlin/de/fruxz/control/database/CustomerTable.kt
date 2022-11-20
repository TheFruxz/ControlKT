package de.fruxz.control.database

import org.jetbrains.exposed.sql.Table

object CustomerTable : Table("customers") {
	val id = uuid("id")
	val attributes = text("attributes")

	override val primaryKey = PrimaryKey(id)
}