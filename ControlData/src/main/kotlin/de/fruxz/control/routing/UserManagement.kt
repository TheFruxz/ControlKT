package de.fruxz.control.routing

import de.fruxz.ascend.extension.data.RandomTagType.MIXED_CASE
import de.fruxz.ascend.extension.data.buildRandomTag
import de.fruxz.control.data.User
import de.fruxz.control.database.Data
import de.fruxz.control.database.UserTable
import de.fruxz.control.database.UserTable.username
import de.fruxz.control.permission.RequiresPermission
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.UUID

object UserManagement {

	@EndPoint
	@RequiresPermission("user.list")
	fun listUsers(page: Int, pageSize: Int = 25) = Data.talk {
		return@talk mapOf(
			"page" to page,
			"pageSize" to pageSize,
			"users" to UserTable.selectAll().limit(pageSize, page.toLong() * pageSize).map { User(it[UserTable.id], it[UserTable.username], it[UserTable.password], it[UserTable.permissions].split(",").toSet()) }
		)
	}

	@EndPoint
	@RequiresPermission("user.get")
	fun getUser(username: String): User? = Data.talk {
		UserTable.select { UserTable.username eq username }
			.firstOrNull()
			?.let { User(
				it[UserTable.id],
				it[UserTable.username],
				"", // intentional blank password
				it[UserTable.permissions].split(",").toSet()
			) }
	}

	@EndPoint
	@RequiresPermission("user.get")
	fun getUser(id: UUID): User? = Data.talk {
		UserTable.select { UserTable.id eq id }
			.firstOrNull()
			?.let { User(
				it[UserTable.id],
				it[UserTable.username],
				"", // intentional blank password
				it[UserTable.permissions].split(",").toSet()
			) }
	}

	@EndPoint // TODO make it work!
	@RequiresPermission("user.create")
	fun createUser(
		username: String,
		password: String = buildRandomTag(15, false, MIXED_CASE),
		permissions: List<String> = emptyList(),
		uuid: UUID = UUID.randomUUID()
	): User {

		Data.talk {
			UserTable.insert {
				it[UserTable.id] = uuid
				it[UserTable.username] = username
				it[UserTable.password] = password
				it[UserTable.permissions] = permissions.joinToString(",")
			}
		}

		return User(uuid, username, password, permissions.toSet())
	}

	fun checkLogin(username: String, password: String) = Data.talk {
		UserTable.select { UserTable.username eq username }
			.firstOrNull()
			?.let { it[UserTable.password] == password } // TODO encrypt password
			?: false
	}

	@EndPoint
	fun hasPermission(id: String, permission: String): Boolean {
		return getUser(id)?.permissions?.contains(permission) == true
	}

	@EndPoint
	@RequiresPermission("test")
	fun test(): String = "This is a test!"

}