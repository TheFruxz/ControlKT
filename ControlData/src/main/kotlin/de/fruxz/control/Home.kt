package de.fruxz.control

import de.fruxz.control.permission.RequiresPermission
import de.fruxz.control.routing.Route

object Home {

	@Route
	@RequiresPermission("root")
	fun user() = UserManagement

}