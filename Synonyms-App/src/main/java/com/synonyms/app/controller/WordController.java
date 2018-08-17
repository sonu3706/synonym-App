package com.synonyms.app.controller;

import com.synonyms.app.exceptions.WordDoesNotExistsException;
import com.synonyms.app.model.SynonymWord;
import com.synonyms.app.service.SynonymService;
import com.synonyms.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/word")
public class WordController {

    private SynonymService synonymService;
    private UserService userService;

    @Autowired
    public WordController(SynonymService synonymService, UserService userService) {
        this.synonymService = synonymService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity saveSynonyms(@RequestBody SynonymWord word) {

        ResponseEntity responseEntity = null;
//        synonymService.addWords(word);

        return responseEntity;
    }

    // get request to fetch the synonyms of a given word which is entered by the User.

    @GetMapping("/{name}")
    public ResponseEntity getSynonyms(@PathVariable String name) {
        ResponseEntity responseEntity = null;

        try {

            List<String> words = synonymService.getAllSynonyms(name);

            responseEntity = new ResponseEntity(words, HttpStatus.OK);
        } catch (WordDoesNotExistsException exception) {

            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);

        }


        return responseEntity;
    }


}
