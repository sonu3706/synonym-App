package com.synonyms.app.repository;

import com.synonyms.app.model.SynonymWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SynonymRepository extends JpaRepository<SynonymWord,Integer> {

    public List<String> getSynonymWordsByWord(String word);
    List<String> findWordByWord(String word);
}
