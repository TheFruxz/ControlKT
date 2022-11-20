package de.fruxz.control.routing

import de.fruxz.control.data.User
import de.fruxz.control.permission.RequiresPermission
import java.util.UUID

object UserManagement {

	fun getUser(username: String): User? = User(UUID.randomUUID(), setOf("*")) // TODO

	fun getUser(id: UUID): User? {
		return getUser("") // TODO
	}

	@EndPoint
	fun hasPermission(id: String, permission: String): Boolean {
		return getUser(id)?.permissions?.contains(permission) == true
	}

	@EndPoint
	@RequiresPermission("test")
	fun test(): String = "This is a test!"

}