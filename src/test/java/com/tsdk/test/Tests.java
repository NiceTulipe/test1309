package com.tsdk.test;

import com.tsdk.test.errors.IncorrectException;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Tests {
private final CharacterCounterApi characterCounterApi;
    Map<Character, Integer> test = new HashMap<>();
@Test
public void incorrectInputNumber(){
    String input = "ddd333";
    Assertions.assertThatThrownBy(() -> characterCounterApi.checkRequestParam(input)).isInstanceOf(IncorrectException.class);
}

    @Test
    public void incorrectInputBlank() {
        String input = " ";
        Assertions.assertThatThrownBy(() -> characterCounterApi.checkRequestParam(input)).isInstanceOf(IncorrectException.class);
    }

    @Test
    public void incorrectInputCharAnotherLanguage() {
        String input = "в";
        Assertions.assertThatThrownBy(() -> characterCounterApi.checkRequestParam(input)).isInstanceOf(IncorrectException.class);
    }

    @Test
    public void correctInputCharOne(){
        String input = "i";
        test = characterCounterApi.countCharacters(input);
        assertEquals(1,test.size());
    }

    @Test
    public void correctInputCharTwo(){
        String input = "iddqd";
        test = characterCounterApi.countCharacters(input);
        assertEquals(3,test.size());
    }

    @Test
    public void correctInputChar(){
        String input = "iddqd";
        test = characterCounterApi.countCharacters(input);
        assertTrue(test.containsValue(3));
        assertTrue(test.containsValue(1));
    }

}
