package de.fruxz.control.database

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
	val id = uuid("id")
	val username = varchar("username", 32)
	val password = varchar("password", 128)

	val permissions = text("permissions")

	override val primaryKey = PrimaryKey(username)
}