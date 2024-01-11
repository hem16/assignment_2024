package com.ms.assignment.wordcounter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class WordcounterProcessorTest {
    @Mock
    private TranslatorService translatorService;
    @InjectMocks
    private WordcounterProcessor wordcounterProcessor;

    @Test
    void should_addWords_with_validation() {
        List<String> words = Arrays.asList("flower","flor","blume");
        Mockito.when(translatorService.translate("flower")).thenReturn("flower");
        Mockito.when(translatorService.translate("flor")).thenReturn("flower");
        Mockito.when(translatorService.translate("blume")).thenReturn("flower");
        wordcounterProcessor.addWords(words);
        verify(translatorService).translate("blume");
        Long actualCount = wordcounterProcessor.getWordCount("flower");
        assertEquals(3,actualCount);
    }

    @Test
    void addWords_NonAlphabeticChars_Ignored(){
        List<String> words = Arrays.asList("flower","123","b!@$#");
        Mockito.when(translatorService.translate("flower")).thenReturn("flower");
        wordcounterProcessor.addWords(words);
        Long actualCount = wordcounterProcessor.getWordCount("flower");
        assertEquals(1,actualCount);
    }

    @Test
    void should_getWords_successfully() {
        String word = "flower";
        Mockito.when(translatorService.translate("flower")).thenReturn("flower");
        wordcounterProcessor.getWordCount(word);
        verify(translatorService).translate("flower");
    }

    @Test
    void getWordCount_WordNotAdded_ReturnsZero() {
        String word = "sunflower";
        Long actualCount = wordcounterProcessor.getWordCount(word);
        assertEquals(0L,actualCount);
        verify(translatorService).translate("sunflower");
    }
}