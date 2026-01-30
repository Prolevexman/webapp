package prolevexman.webapp.modifyprocesstests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.web.bind.annotation.PathVariable;
import prolevexman.webapp.service.strategy.ModifyProcess;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты замены гласных")
public class VowelReplacementTests {

    private ModifyProcess modifyProcess;

    @BeforeEach
    void setUp() {
        modifyProcess = new ModifyProcess();
    }
    @ParameterizedTest(name = "'{0}' -> '{1}'")
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
}
