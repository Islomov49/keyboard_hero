package uz.demo.keyboardhero.utils.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument

fun String.toRoute(vararg queryParams: Pair<String, Any?>): String {
    val builder = StringBuilder(this)
    builder.append("?")
    for (pair in queryParams) {
        builder.append("${pair.first}=${Uri.encode(pair.second.toString())}")
        builder.append("&")
    }
    builder.removeSuffix("&")
    builder.removeSuffix("?")
    return builder.toString()
}

fun String.toRouteDestination(keys: List<NamedNavArgument>): String {
    val builder = StringBuilder(this)
    builder.append("?")
    for (key in keys) {
        builder.append("${key.name}={${key.name}}")
        builder.append("&")
    }
    builder.removeSuffix("&")
    builder.removeSuffix("?")
    return builder.toString()
}

fun SavedStateHandle.read(key: String): String {
    val value: String = checkNotNull(this[key])
    return Uri.decode(value)
}

fun <T> SavedStateHandle.read(key: String): T {
    return checkNotNull(this[key])
}

fun SavedStateHandle.readOptional(key: String): String? {
    val value: String? = this[key]
    return if (value != null) Uri.decode(value) else null
}

fun <T> SavedStateHandle.readOptional(key: String): T? {
    return this[key]
}
