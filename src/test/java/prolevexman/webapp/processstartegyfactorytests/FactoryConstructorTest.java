package prolevexman.webapp.processstartegyfactorytests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.factory.ProcessStrategyFactory;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;
import prolevexman.webapp.testsupport.ModifyStrategyStub;
import prolevexman.webapp.testsupport.TranslateStrategyStub;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryConstructorTest {

    private ProcessStrategyFactory factory;

    @Test
    @DisplayName("конструктор должен принимать пустой список")
    void shouldAcceptEmptyList() {
        factory = new ProcessStrategyFactory(Collections.emptyList());

        assertThat(factory).isNotNull();
    }

    @Test
    @DisplayName("конструктор должен сохранять все переданные стратегии")
    void shouldSaveAllStrategies() {
        List<ProcessExecutionStrategy> stratigies = List.of(new ModifyStrategyStub(), new TranslateStrategyStub());

        factory = new ProcessStrategyFactory(stratigies);

        assertThat(factory.getStrategy(ProcessType.MODIFY)).isInstanceOf(ModifyStrategyStub.class);
        assertThat(factory.getStrategy(ProcessType.TRANSLATE)).isInstanceOf(TranslateStrategyStub.class);
    }
}
