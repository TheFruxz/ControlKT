package de.fruxz.control

import de.fruxz.control.data.User

object UserManagement {

	fun getUser(id: String): User? {
		return null // TODO
	}

	fun hasPermission(id: String, permission: String): Boolean {
		return getUser(id)?.permissions?.contains(permission) == true
	}

}