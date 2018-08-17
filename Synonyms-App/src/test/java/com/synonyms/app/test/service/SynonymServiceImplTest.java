package com.synonyms.app.test.service;

import com.synonyms.app.model.SynonymWord;
import com.synonyms.app.repository.SynonymRepository;
import com.synonyms.app.service.SynonymServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class SynonymServiceImplTest {

    @MockBean
    private SynonymWord synonymWord;
    @Mock
    private SynonymRepository synonymRepository;

    @InjectMocks
    private SynonymServiceImpl synonymServiceImpl;

    List<String> words = new ArrayList<>();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        SynonymWord synonymWord = new SynonymWord(1, "chandan", "prompt");
        words.add(synonymWord.getSynonym());

        SynonymWord synonymWord1 = new SynonymWord(2, "fast", "hurried");
        words.add(synonymWord1.getSynonym());
        SynonymWord synonymWord2 = new SynonymWord(3, "fast", "rapid");
        words.add(synonymWord2.getSynonym());
    }

@Test
public void getAllSynonymsTest() throws Exception{

        Mockito.when(synonymRepository.findWordByWord("fast")).thenReturn(words);
        List<String> fetchedWords = synonymServiceImpl.getAllSynonyms("fast");
        Assert.assertEquals(words,fetchedWords);
}

}
