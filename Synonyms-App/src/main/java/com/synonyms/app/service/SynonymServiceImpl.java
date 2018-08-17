package com.synonyms.app.service;

import com.synonyms.app.exceptions.WordDoesNotExistsException;
import com.synonyms.app.model.SynonymWord;
import com.synonyms.app.repository.SynonymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SynonymServiceImpl implements SynonymService {

    private SynonymRepository synonymRepository;

    @Autowired
    public SynonymServiceImpl(SynonymRepository synonymRepository) {
        this.synonymRepository = synonymRepository;
    }


    @Override
    public List<String> getAllSynonyms(String word) throws WordDoesNotExistsException {
        List<String> synonymWords = new ArrayList<>();
        if (!synonymRepository.findWordByWord(word).isEmpty()) {

            List<String> words = synonymRepository.getSynonymWordsByWord(word);
            Iterator itr = words.iterator();
            while (itr.hasNext()) {
                SynonymWord w1 = (SynonymWord) itr.next();
                synonymWords.add(w1.getSynonym());
            }

        }

        else {
            throw  new WordDoesNotExistsException("Word does not exists");
        }


        return synonymWords;
    }


}
