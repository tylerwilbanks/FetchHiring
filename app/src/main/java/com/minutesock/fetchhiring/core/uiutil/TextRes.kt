package com.minutesock.fetchhiring.core.uiutil

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.ImmutableList

sealed class TextRes {
    data class Raw(
        val value: String
    ) : TextRes()

    data class StringRes(
        @androidx.annotation.StringRes val resource: Int,
        val args: ImmutableList<Any>? = null
    ) : TextRes()

    fun asRawString(): String {
        return when (this) {
            is Raw -> value
            is StringRes -> "Failed to retrieve string resource: $resource"
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is Raw -> value
            is StringRes -> {
                if (args == null) {
                    stringResource(resource)
                } else {
                    stringResource(resource, args)
                }
            }
        }
    }

    fun empty() = Raw("")

    companion object {
        fun empty() = Raw("")
    }
}