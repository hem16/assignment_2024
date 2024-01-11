package com.ms.assignment.wordcounter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class WordcounterProcessor implements WordcounterService{
    private ConcurrentHashMap<String, Long> wordMap;
    @Autowired
    final private TranslatorService translatorService;

    public WordcounterProcessor(TranslatorService translatorService) {
        this.wordMap = new ConcurrentHashMap<>();
        this.translatorService = translatorService;
    }

    public void addWords(List<String> words) {
        wordMap = words
                .parallelStream()
                        .filter(this::isValidWord)
                        .collect(Collectors
                                .groupingByConcurrent(w -> translatorService.translate(w.toLowerCase()),
                                        ConcurrentHashMap::new,
                                        Collectors.counting()));

    }

    public Long getWordCount(String word){
        String translatedWord = translatorService.translate(word);
        if(translatedWord != null) {
            return wordMap.get(translatedWord);
        }
        return 0L;
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }

}
