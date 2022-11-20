package de.fruxz.control.permission

import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * This interface defines, that a function requires an
 * executor, to have this [permission] to execute this!
 */
@Target(FUNCTION)
@Repeatable
annotation class RequiresPermission(
	val permission: String,
)
