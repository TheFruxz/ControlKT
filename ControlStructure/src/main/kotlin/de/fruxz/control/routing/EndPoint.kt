package de.fruxz.control.routing

import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * This annotation defines, that a function
 * is an endpoint, to execute and return.
 */
@Target(FUNCTION)
annotation class EndPoint
