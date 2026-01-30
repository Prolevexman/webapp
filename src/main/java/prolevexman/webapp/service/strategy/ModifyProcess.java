package prolevexman.webapp.service.strategy;

import org.springframework.stereotype.Service;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.enums.ProcessType;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class ModifyProcess implements ProcessExecutionStrategy{

    @Override
    public ProcessType getType() {
        return ProcessType.MODIFY;
    }

    @Override
    public CompletableFuture<String> execute(String input) {
        String result = modifyString(input);

        return CompletableFuture.completedFuture(result);
    }

    private String modifyString(String input) {
        char[] result = input.toCharArray();
        int leftPointer = 0;
        int rightPointer = result.length - 1;

        while (leftPointer <= rightPointer) {
            char leftChar = input.charAt(leftPointer);
            char rightChar = input.charAt(rightPointer);

            result[leftPointer] = replaceVowel(rightChar);
            result[rightPointer] = replaceVowel(leftChar);

            leftPointer++;
            rightPointer--;
        }
        return new String(result);
    }

    private static final Set<Character> VOWELS = Set.of(
            'a','e','i','o','u',
            'а','е','ё','и','о','у','ы','э','ю','я');
    private char replaceVowel(char c) {
        boolean isUpper = Character.isUpperCase(c);
        char lower = Character.toLowerCase(c);
        if(!VOWELS.contains(lower)) {
            return c;
        }
        char replaced = nextAlphabetChar(lower);
        return isUpper ? Character.toUpperCase(replaced) : replaced;
    }

    private static final String RU_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private char nextAlphabetChar(char c) {
        if(c >= 'a' && c <= 'z') {
            return c == 'z' ? 'a' : (char) (c + 1);
        }

        int index = RU_ALPHABET.indexOf(c);
        if(index != -1) {
            return RU_ALPHABET.charAt((index + 1) % RU_ALPHABET.length());
        }
        return c;
    }
}
