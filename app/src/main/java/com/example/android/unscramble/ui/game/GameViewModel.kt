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

    lateinit var currentWord: String
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
        currentWord = allWordsList.random()

        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordList.contains(currentWord)) getNextWord()
        else {
            _currentScrambledWord = tempWord.toString()
            ++_currentWordCount
            wordList.add(currentWord)
        }
    }

    private fun canSkipToNextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

}