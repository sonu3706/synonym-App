package com.synonyms.app.service;

import com.synonyms.app.exceptions.WordDoesNotExistsException;
import com.synonyms.app.model.SynonymWord;

import java.util.List;

public interface SynonymService {

    public List<String> getAllSynonyms(String word) throws WordDoesNotExistsException;
}
