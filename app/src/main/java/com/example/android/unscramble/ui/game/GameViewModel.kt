package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _score = 0
    val score: Int get() = _score

    private var _currentWordCount = 0
    val currentWordCount: Int get() = _currentWordCount

    private lateinit var _currentScrambledWord: String
    val currentScrambleWord: String get() = _currentScrambledWord

    private lateinit var _currentWord: String
    val currentWord: String get() = _currentWord

    private var wordList: MutableList<String> = mutableListOf()

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord() {
        _currentWord = allWordsList.random()

        val tempWord = _currentWord.toCharArray()
        tempWord.shuffle()
        while (String(tempWord).equals(_currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordList.contains(_currentWord)) getNextWord()
        else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordList.add(_currentWord)
        }
    }

    fun canSkipToNextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(word: String): Boolean {
        if (word.equals(_currentWord, ignoreCase = true)) {
            increaseScore()
            return true
        }
        return false
    }

}