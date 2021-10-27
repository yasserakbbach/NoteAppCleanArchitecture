package com.yasserakbbach.core.usecase

import com.yasserakbbach.core.data.Note

class GetWordCount {

    operator fun invoke(note: Note) =
        getWordCount(note.title).plus(getWordCount(note.content))

    private fun getWordCount(str: String) =
        str.split(" ", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
}