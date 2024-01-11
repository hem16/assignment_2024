package com.ms.assignment.wordcounter.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface WordcounterService {
    public void addWords(List<String> words);
    public Long getWordCount(String word);
}
