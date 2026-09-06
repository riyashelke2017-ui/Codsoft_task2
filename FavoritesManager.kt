package com.example.quoteoftheday

import android.content.Context

class FavoritesManager(context: Context) {

    private val preferences =
        context.getSharedPreferences(
            "quote_preferences",
            Context.MODE_PRIVATE
        )

    private val separator = "|||"

    fun isFavorite(quote: Quote): Boolean {

        val favorites =
            preferences.getStringSet(
                "favorites",
                emptySet()
            ) ?: emptySet()

        return favorites.any {
            it == quote.text + separator + quote.author
        }
    }

    fun toggleFavorite(quote: Quote) {

        val favorites =
            (preferences.getStringSet(
                "favorites",
                emptySet()
            ) ?: emptySet()).toMutableSet()

        val value =
            quote.text + separator + quote.author

        if (favorites.contains(value)) {
            favorites.remove(value)
        } else {
            favorites.add(value)
        }

        preferences.edit()
            .putStringSet("favorites", favorites)
            .apply()
    }

    fun getFavorites(): List<Quote> {

        val favorites =
            preferences.getStringSet(
                "favorites",
                emptySet()
            ) ?: emptySet()

        return favorites.mapNotNull {

            val parts = it.split(separator)

            if (parts.size == 2) {
                Quote(parts[0], parts[1])
            } else {
                null
            }

        }
    }

    fun removeFavorite(quote: Quote) {

        val favorites =
            (preferences.getStringSet(
                "favorites",
                emptySet()
            ) ?: emptySet()).toMutableSet()

        favorites.remove(
            quote.text + separator + quote.author
        )

        preferences.edit()
            .putStringSet("favorites", favorites)
            .apply()
    }
}
