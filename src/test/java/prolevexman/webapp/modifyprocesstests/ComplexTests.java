package prolevexman.webapp.modifyprocesstests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.service.strategy.ModifyProcess;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Комплексные тесты")
public class ComplexTests {

    private ModifyProcess modifyProcess;

    @BeforeEach
    void setUp() {
        modifyProcess = new ModifyProcess();
    }

    @Test
    @DisplayName("ПривЕт wOrlD_01 -> 10_DlrPw тЁвйрП")
    void shouldProcessComplexText() {
        String result = modifyProcess.execute("ПривЕт wOrlD_01").join();
        assertThat(result).isEqualTo("10_DlrPw тЁвйрП");
    }
}
