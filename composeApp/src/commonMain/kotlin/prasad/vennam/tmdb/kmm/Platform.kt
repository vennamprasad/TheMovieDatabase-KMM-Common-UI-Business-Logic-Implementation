package prasad.vennam.tmdb.kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform