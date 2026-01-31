package prolevexman.webapp.processstartegyfactorytests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.factory.ProcessStrategyFactory;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;
import prolevexman.webapp.testsupport.ModifyStrategyStub;
import prolevexman.webapp.testsupport.TranslateStrategyStub;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("getStrategy()")
public class FactoryGetStrategyTest {

    private ProcessStrategyFactory factory;

    @BeforeEach
    void setUp() {
        List<ProcessExecutionStrategy> strategies = List.of(new ModifyStrategyStub(), new TranslateStrategyStub());
        factory = new ProcessStrategyFactory(strategies);
    }

    @Test
    @DisplayName("должен возвращать Modify стратегию")
    void shouldReturnModifyStrategy() {
        ProcessExecutionStrategy strategy = factory.getStrategy(ProcessType.MODIFY);

        assertThat(strategy).isNotNull();
        assertThat(strategy.getType()).isEqualTo(ProcessType.MODIFY);
    }

    @Test
    @DisplayName("должен возвращать Translate стратегию")
    void shouldReturnTranslateStrategy() {
        ProcessExecutionStrategy strategy = factory.getStrategy(ProcessType.TRANSLATE);

        assertThat(strategy).isNotNull();
        assertThat(strategy.getType()).isEqualTo(ProcessType.TRANSLATE);
    }

    @Test
    @DisplayName("должен возвращать тот же экземпляр при повторном вызове")
    void shouldReturnSameStrategyInstance() {
        ProcessExecutionStrategy strategy1 = factory.getStrategy(ProcessType.MODIFY);
        ProcessExecutionStrategy strategy2 = factory.getStrategy(ProcessType.MODIFY);

        assertThat(strategy1).isSameAs(strategy2);
    }
}
