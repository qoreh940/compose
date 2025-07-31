package com.chch.mycompose.ui.screen.selection

enum class Gender(val displayName: String) {
    FEMALE("Female"), MALE("Male"), NON_BINARY("Non-binary");

    companion object {
        fun values() = entries.toTypedArray()
    }
}