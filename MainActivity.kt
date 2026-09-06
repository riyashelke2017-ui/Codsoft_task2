package com.example.quoteoftheday

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var quoteText: TextView
    private lateinit var authorText: TextView
    private lateinit var newQuoteButton: Button
    private lateinit var favoriteButton: Button
    private lateinit var shareButton: Button
    private lateinit var favoritesButton: Button

    private lateinit var favoritesManager: FavoritesManager

    private var currentQuote: Quote? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        quoteText = findViewById(R.id.quoteText)
        authorText = findViewById(R.id.authorText)

        newQuoteButton = findViewById(R.id.newQuoteButton)
        favoriteButton = findViewById(R.id.favoriteButton)
        shareButton = findViewById(R.id.shareButton)
        favoritesButton = findViewById(R.id.favoritesButton)

        favoritesManager =
            FavoritesManager(this)

        currentQuote =
            QuoteRepository.getQuoteOfTheDay()

        displayQuote(currentQuote!!)

        newQuoteButton.setOnClickListener {

            currentQuote =
                QuoteRepository.getRandomQuote(
                    currentQuote!!
                )

            displayQuote(currentQuote!!)
        }

        favoriteButton.setOnClickListener {

            val quote = currentQuote ?: return@setOnClickListener

            favoritesManager.toggleFavorite(quote)

            updateFavoriteButton(quote)

            Toast.makeText(
                this,
                if (favoritesManager.isFavorite(quote))
                    "Added to Favorites ♥"
                else
                    "Removed from Favorites",
                Toast.LENGTH_SHORT
            ).show()
        }

        shareButton.setOnClickListener {

            val quote = currentQuote ?: return@setOnClickListener

            val text =
                "“${quote.text}”\n\n— ${quote.author}"

            val shareIntent =
                Intent(Intent.ACTION_SEND)

            shareIntent.type = "text/plain"

            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                text
            )

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Share this quote"
                )
            )
        }

        favoritesButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    FavoritesActivity::class.java
                )

            startActivity(intent)
        }
    }

    private fun displayQuote(quote: Quote) {

        quoteText.text = quote.text

        authorText.text =
            "— ${quote.author}"

        updateFavoriteButton(quote)
    }

    private fun updateFavoriteButton(quote: Quote) {

        if (favoritesManager.isFavorite(quote)) {

            favoriteButton.text =
                "♥  Saved"

        } else {

            favoriteButton.text =
                "♡  Favorite"
        }
    }
}
