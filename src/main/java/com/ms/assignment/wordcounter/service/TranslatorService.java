package com.ms.assignment.wordcounter.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TranslatorService {
    //Assuming translation will be done via external class
    public String translate(String word){
        //Here - Below is the dummy implementation of Translator service
        Map<String,String> dict = new HashMap<>();
        dict.put("flower","flower");
        dict.put("flor","flower");
        dict.put("blume","flower");
        dict.put("hello","hello");
        dict.put("ola","hello");
        return dict.getOrDefault(word,word);
    }
}
