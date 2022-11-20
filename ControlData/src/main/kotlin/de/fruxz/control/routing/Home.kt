package de.fruxz.control.routing

import de.fruxz.control.permission.RequiresPermission

object Home {

	@Route
	@RequiresPermission("users")
	fun user() = UserManagement

	@Route
	@RequiresPermission("customers")
	fun customer() = CustomerManagement

}