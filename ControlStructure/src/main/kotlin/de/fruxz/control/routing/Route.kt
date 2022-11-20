package de.fruxz.control.routing

import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * This annotation defines, that a function
 * is a route to other functions (inside an object)
 */
@Target(FUNCTION)
annotation class Route
