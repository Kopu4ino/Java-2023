package edu.project1;

import java.util.List;
import java.util.Random;

public class WordDictionary {
    private final List<String> words;
    private static final int MIN_WORD_LENGTH = 3;

    public WordDictionary(List<String> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }
        this.words = words;
    }

    public String getWord() {
        Random rd = new Random();
        String guessWord = this.words.get(rd.nextInt(this.words.size())).toLowerCase();
        if (guessWord.length() < MIN_WORD_LENGTH) {
            throw new IllegalArgumentException("Выбранное слово слишком короткое: " + guessWord);
        }
        return guessWord;
    }
}
