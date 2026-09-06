package com.example.quoteoftheday

import java.util.Calendar

object QuoteRepository {

    private val quotes = listOf(

        Quote(
            "wo things are infinite: the universe and human stupidity; and I'm not sure about the universe.",
            "Albert Einstein"
        ),

        Quote(
            "You only live once, but if you do it right, once is enough.",
            "Mae West"
        ),

        Quote(
            "Money follow's My Brothurrr, money follow's.",
            "Ravi Kisan"
        ),

        Quote(
            "Ask not what your country can do for you – ask what you can do for your country",
            "John F. Kennedy"
        ),

        Quote(
            "A room without books is like a body without a soul.",
            "Marcus Tullius Cicero"
        ),

        Quote(
            "Today a reader, tomorrow a leader.",
            "Margaret Fuller"
        ),

        Quote(
            "A book is a dream that you hold in your hand.",
            "Neil Gaiman"
        ),

        Quote(
            "I came, I saw, I conquered.",
            " Julius Caesar"
        ),

        Quote(
            "To be, or not to be, that is the question.",
            "William Shakespeare."
        ),

        Quote(
            "Stay hungry, stay foolish.",
            "Steve Jobs"
        ),

        Quote(
            "Think before you speak. Read before you think.",
            "Fran Lebowitz"
        ),

        Quote(
            "E = mc² ✨.",
            "Albert Einstein"
        )
    )

    fun getQuoteOfTheDay(): Quote {

        val calendar = Calendar.getInstance()

        val day = calendar.get(Calendar.DAY_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)

        val index = (day + year) % quotes.size

        return quotes[index]
    }

    fun getRandomQuote(currentQuote: Quote): Quote {

        var newQuote = quotes.random()

        while (newQuote == currentQuote) {
            newQuote = quotes.random()
        }

        return newQuote
    }
}
