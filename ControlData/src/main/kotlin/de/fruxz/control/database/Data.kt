package de.fruxz.control.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

object Data {

	val database by lazy { Database.connect("jdbc:mysql://localhost/controlkt", user = "root", password = "root") }

	fun <T> talk(block: Transaction.() -> T) = transaction(database, statement = block)

	fun initialize() = talk {
		SchemaUtils.create(CustomerTable)
	}

}