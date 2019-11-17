package com.emoke;

public class Word {
    private int idWord;
    private String word;
    private int idWordCategory;

    public Word(int idWord, String word, int idWordCategory) {
        this.idWord = idWord;
        this.word = word;
        this.idWordCategory = idWordCategory;
    }

    public int getIdWord() {
        return idWord;
    }

    public String getWord() {
        return word;
    }

    public int getIdWordCategory() {
        return idWordCategory;
    }
}
