package de.fruxz.control.routing

import de.fruxz.ascend.extension.data.fromJsonString
import de.fruxz.ascend.extension.data.toJsonString
import de.fruxz.control.data.Customer
import de.fruxz.control.data.attribute.AttributeType.*
import de.fruxz.control.data.attribute.CustomerAttribute
import de.fruxz.control.database.CustomerTable
import de.fruxz.control.database.Data
import de.fruxz.control.permission.RequiresPermission
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.UUID

object CustomerManagement {

	@EndPoint
	@RequiresPermission("customer.read")
	fun getCustomer(id: String): Customer? = Data.talk {
		CustomerTable
			.select { CustomerTable.id eq UUID.fromString(id) }
			.firstOrNull()
			?.let { Customer(id, it[CustomerTable.attributes].fromJsonString()) }
	}

	@EndPoint
	@RequiresPermission("customer.create")
	fun addCustomer(name: String, address: String, email: String?, phone: String?) = Data.talk {
		val id = UUID.randomUUID()

		CustomerTable.insert {
			it[CustomerTable.id] = id
			it[CustomerTable.attributes] = buildList {
				add(CustomerAttribute("name", "Name", name, NAME))
				add(CustomerAttribute("address", "Address", address, ADDRESS))
				if (email != null) add(CustomerAttribute("email", "E-Mail", email, MAIL))
				if (phone != null) add(CustomerAttribute("phone", "Phone", phone, FON))
			}.toJsonString()
		}

		getCustomer(id.toString())
	}

}