package com.ms.assignment.wordcounter.service;

import java.util.List;

public interface WordcounterService {
    void addWords(List<String> words);
    Long getWordCount(String word);
}
