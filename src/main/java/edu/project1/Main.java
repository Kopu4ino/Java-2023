package edu.project1;

import java.util.List;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary(List.of("hello", "world", "java", "hangman"));
//        WordDictionary dictionary = new WordDictionary(List.of("h", "elo"));
        ConsoleHangman game = new ConsoleHangman(dictionary);
        game.run();
    }
}
