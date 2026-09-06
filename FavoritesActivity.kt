package com.example.quoteoftheday

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favoritesManager: FavoritesManager
    private lateinit var favoritesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorites)

        favoritesManager = FavoritesManager(this)

        favoritesContainer =
            findViewById(R.id.favoritesContainer)

        displayFavorites()
    }

    private fun displayFavorites() {

        favoritesContainer.removeAllViews()

        val favorites =
            favoritesManager.getFavorites()

        if (favorites.isEmpty()) {

            val emptyText = TextView(this)

            emptyText.text =
                "No favorite quotes yet ♡\n\nTap the Favorite button to save a quote."

            emptyText.textSize = 18f
            emptyText.setTextColor(
                getColor(R.color.soft_text)
            )

            emptyText.gravity =
                android.view.Gravity.CENTER

            emptyText.setPadding(
                30,
                100,
                30,
                100
            )

            favoritesContainer.addView(emptyText)

            return
        }

        for (quote in favorites) {

            val card =
                CardView(this)

            card.radius = 24f
            card.cardElevation = 6f

            val cardLayout =
                LinearLayout(this)

            cardLayout.orientation =
                LinearLayout.VERTICAL

            cardLayout.setPadding(
                24,
                24,
                24,
                24
            )

            val quoteText =
                TextView(this)

            quoteText.text =
                "“${quote.text}”"

            quoteText.textSize = 19f

            quoteText.setTextColor(
                getColor(R.color.dark_text)
            )

            quoteText.typeface =
                android.graphics.Typeface.SERIF

            val authorText =
                TextView(this)

            authorText.text =
                "— ${quote.author}"

            authorText.textSize = 14f

            authorText.setTextColor(
                getColor(R.color.warm_brown)
            )

            authorText.setPadding(
                0,
                16,
                0,
                0
            )

            val removeButton =
                android.widget.Button(this)

            removeButton.text =
                "♥  Remove from Favorites"

            removeButton.setTextColor(
                getColor(R.color.dark_text)
            )

            removeButton.setOnClickListener {

                favoritesManager.removeFavorite(
                    quote
                )

                displayFavorites()
            }

            cardLayout.addView(quoteText)
            cardLayout.addView(authorText)
            cardLayout.addView(removeButton)

            card.addView(cardLayout)

            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            params.setMargins(
                0,
                0,
                0,
                18
            )

            card.layoutParams = params

            favoritesContainer.addView(card)
        }
    }

    override fun onResume() {
        super.onResume()

        displayFavorites()
    }
}
