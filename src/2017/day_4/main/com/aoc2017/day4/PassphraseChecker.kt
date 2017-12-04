package com.aoc2017.day4

object PassphraseChecker {

    private fun GetWords(string: String): List<String> {
        return string.split(Regex("\\s+"))
    }

    fun ValidatePassphrase(string: String): Boolean {
        val words = GetWords(string)

        return words.size == words.distinct().size
    }

    fun ValidatePassphraseAdvanced(string: String): Boolean {
        val words = GetWords(string)

        return words.size == words
                .map { it.map { it.toString() }.sorted() }
                .distinct()
                .size
    }
}