package prolevexman.webapp.modifyprocesstests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.service.strategy.ModifyProcess;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты граничных случаев")
public class EdgeCasesTests {

    private ModifyProcess modifyProcess;

    @BeforeEach
    void setUp() {
        modifyProcess = new ModifyProcess();
    }

    @Test
    @DisplayName("должен обрабатывать пустую строку")
    void shouldHandleEmptyString() {
        String result = modifyProcess.execute("").join();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Должен обрабатывать один символ")
    void shouldHandleSingleCharacter() {
        String result = modifyProcess.execute("X").join();
            assertThat(result).isEqualTo("X");
    }

    @Test
    @DisplayName("Должен обрабатывать один гласный символ")
    void shouldHandleSingleVowel() {
        String result = modifyProcess.execute("a").join();
        assertThat(result).isEqualTo("b");
    }

    @Test
    @DisplayName("должен обрабатывать пробелы")
    void shouldHandleSpaces() {
        String result = modifyProcess.execute("a b").join();
        assertThat(result).isEqualTo("b b");
    }
}
