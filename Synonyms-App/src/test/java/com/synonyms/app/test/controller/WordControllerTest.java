package com.synonyms.app.test.controller;

import com.synonyms.app.controller.WordController;
import com.synonyms.app.exceptions.WordDoesNotExistsException;
import com.synonyms.app.model.SynonymWord;
import com.synonyms.app.service.SynonymService;
import com.synonyms.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
public class WordControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private SynonymService synonymService;
    @MockBean
    private UserService userService;

    @InjectMocks
    private WordController wordController;
    List<String> words = new ArrayList<>();


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wordController).build();

        SynonymWord synonymWord = new SynonymWord(1, "fast", "prompt");
        words.add(synonymWord.getSynonym());

        SynonymWord synonymWord1 = new SynonymWord(2, "fast", "hurried");
        words.add(synonymWord1.getSynonym());
        SynonymWord synonymWord2 = new SynonymWord(3, "fast", "rapid");
        words.add(synonymWord2.getSynonym());



    }

    @Test
    public void testGetSynonyms() throws Exception{


        Mockito.when(synonymService.getAllSynonyms("fast")).thenReturn(words);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/word/fast")
                                               .contentType(MediaType.APPLICATION_JSON))
                                               .andExpect(MockMvcResultMatchers.status().isOk())
                                               .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void testGetSynonymsFailure() throws Exception{


        Mockito.when(synonymService.getAllSynonyms("Apocalypse")).thenThrow(WordDoesNotExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/word/Apocalypse")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }

}
