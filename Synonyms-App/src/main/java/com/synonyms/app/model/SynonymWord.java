package com.synonyms.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SynonymWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wordId;
    private String word;
    private String synonym;

    public SynonymWord() {
    }

    public SynonymWord(int wordId, String word, String synonym) {
        this.wordId = wordId;
        this.word = word;
        this.synonym = synonym;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public String toString() {
        return "SynonymWord{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", synonym='" + synonym + '\'' +
                '}';
    }
}
