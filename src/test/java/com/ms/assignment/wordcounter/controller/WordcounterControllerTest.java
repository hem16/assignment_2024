package com.ms.assignment.wordcounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.assignment.wordcounter.service.WordcounterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordcounterController.class)
public class WordcounterControllerTest {

@Autowired
private MockMvc mockMvc;

@MockBean
private WordcounterService wordcounterService;

@InjectMocks
private WordcounterController wordcounterController;

@Autowired
ObjectMapper objectMapper;

@Test
public void addWords_ValidWords_ReturnSUCCESS() throws Exception {
    String testStr = "flower flor";
    List words = Arrays.asList(testStr.trim().split("\\s+"));

    mockMvc.perform(post("/word-counter/add-words")
            .contentType(MediaType.APPLICATION_JSON)
            .content(testStr))
            .andExpect(status().isCreated());
    verify(wordcounterService).addWords(words);
}

@Test
public void getWordCount_ReturnCount() throws Exception {
    String word = "flower";

    mockMvc.perform(get("/word-counter/word/{word}",word))
            .andExpect(status().isOk());
    verify(wordcounterService).getWordCount(word);
}

}
