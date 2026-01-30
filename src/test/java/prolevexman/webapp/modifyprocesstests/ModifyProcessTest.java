package prolevexman.webapp.modifyprocesstests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.strategy.ModifyProcess;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class ModifyProcessTest {

    private ModifyProcess modifyProcess;

    @BeforeEach
    void setUp() {
        modifyProcess = new ModifyProcess();
    }

    @Test
    @DisplayName("getType должен возвращать MODIFY")
    void getTypeShouldReturnModify() {
        assertThat(modifyProcess.getType()).isEqualTo(ProcessType.MODIFY);
    }

    @Test
    @DisplayName("execute должен возвращать CompletableFuture")
    void executeShouldReturnCompletableFuture() {
        CompletableFuture<String> result = modifyProcess.execute("ttt");
        assertThat(result).isCompletedWithValue("ttt");
    }
}
