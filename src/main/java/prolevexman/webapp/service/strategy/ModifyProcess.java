package prolevexman.webapp.service.strategy;

import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.enums.ProcessType;

import java.util.Set;

public class ModifyProcess implements ProcessExecutionStrategy{

    private final ProcessInstanceDao processInstanceDao;

    public ModifyProcess(ProcessInstanceDao processInstanceDao) {
        this.processInstanceDao = processInstanceDao;
    }

    @Override
    public ProcessType getType() {
        return ProcessType.MODIFY;
    }

    @Override
    public void execute(ProcessInstance processInstance) {
        String input = processInstance.getInputData();
        String result = modifyString(input);

        processInstance.setResult(result);
        processInstanceDao.update(processInstance);
    }

    private String modifyString(String input) {
        char[] result = input.toCharArray();
        int leftPointer = 0;
        int rightPointer = result.length - 1;

        while (leftPointer < rightPointer) {
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
        char lower = Character.toLowerCase(c);
        if(!VOWELS.contains(lower)) {
            return c;
        }
        return nextAlphabetChar(c);
    }

    private char nextAlphabetChar(char c) {
        if(c >= 'a' && c <= 'z') {
            return c == 'z' ? 'a' : (char) (c + 1);
        }

        if(c >= 'а' && c <= 'я') {
            return c == 'я' ? 'а' : (char) (c + 1);
        }
        return c;
    }
}
