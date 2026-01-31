package prolevexman.webapp.modifyprocesstests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import prolevexman.webapp.service.strategy.ModifyProcess;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты замены гласных")
public class VowelReplacementTests {

    private ModifyProcess modifyProcess;

    @BeforeEach
    void setUp() {
        modifyProcess = new ModifyProcess();
    }
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "a, b",
            "e, f",
            "i, j",
            "o, p",
            "u, v"
    })
    @DisplayName("замена английских гласных на следующую букву")
    void shouldReplaceEnglishVowels(String input, String expected) {
        String actual = modifyProcess.execute(input).join();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "а, б",
            "е, ё",
            "ё, ж",
            "и, й",
            "о, п",
            "у, ф",
            "ы, ь",
            "э, ю",
            "ю, я",
            "я, а"
    })
    @DisplayName("замена русских гласных на следующую букву")
    void shouldReplaceRussianVowels(String input, String expected) {
        String actual = modifyProcess.execute(input).join();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Должен сохранять регистр при замене гласных")
    void shouldPreserveCaseWhenReplacingVowels() {
        String result = modifyProcess.execute("Ae").join();
        assertThat(result).isEqualTo("fB");
    }

    @Test
    @DisplayName("Не должен заменять согласные")
    void shouldNotReplaceConsonants() {
        String result = modifyProcess.execute("BbczZ").join();
        assertThat(result).isEqualTo("ZzcbB");
    }
}
