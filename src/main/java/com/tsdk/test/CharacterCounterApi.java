package com.tsdk.test;

import com.tsdk.test.errors.IncorrectException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CharacterCounterApi {

    @GetMapping(value = "/count")
    public Map<Character, Integer> countCharacters(
            @RequestParam String input
    ) {
        checkRequestParam(input);
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> sortedCharList = new ArrayList<>(charMap.entrySet());
        sortedCharList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<Character, Integer> sortedChar = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> ch : sortedCharList) {
            sortedChar.put(ch.getKey(), ch.getValue());
        }
        return sortedChar;
    }

    public void checkRequestParam(String input) {
        if (!input.matches("[a-zA-Z]+")) {
            throw new IncorrectException("Invalid parameter. Only letters are allowed.");
        }
    }
}

