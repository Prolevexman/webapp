package prolevexman.webapp.modifyprocesstests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.service.strategy.ModifyProcess;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест переворота строки")
public class ReverseTests {

    private ModifyProcess modifyProcess;

    @BeforeEach
    void setUp() {
        modifyProcess = new ModifyProcess();
    }

    @Test
    @DisplayName("без гласных")
    void shouldReverseStringWithoutVowels() {
        String actual = modifyProcess.execute("bcdf").join();
        assertThat(actual).isEqualTo("fdcb");
    }

    @Test
    @DisplayName("нечетной длины")
    void shouldReverseOddLengthString() {
        String actual = modifyProcess.execute("12345").join();
        assertThat(actual).isEqualTo("54321");
    }
}
