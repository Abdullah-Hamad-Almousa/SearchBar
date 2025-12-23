package com.mysticbyte.searchbar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform