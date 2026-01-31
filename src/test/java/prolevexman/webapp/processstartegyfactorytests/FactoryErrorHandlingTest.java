package prolevexman.webapp.processstartegyfactorytests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.factory.ProcessStrategyFactory;
import prolevexman.webapp.testsupport.ModifyStrategyStub;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Обработка ошибок")
public class FactoryErrorHandlingTest {

    private ProcessStrategyFactory factory;

    @Test
    @DisplayName("должен бросать исключение для несуществующего типа")
    void shouldThrowForUnregisteredType() {
        factory = new ProcessStrategyFactory(List.of(new ModifyStrategyStub()));

        assertThatThrownBy(() -> factory.getStrategy(ProcessType.TRANSLATE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Стратегия для данного типа не найдена: ")
                .hasMessageContaining("TRANSLATE");
    }

    @Test
    @DisplayName("должен бросать исключение когда стратегий нет")
    void shouldThrowWhenNoStrategies() {
        factory = new ProcessStrategyFactory(Collections.emptyList());

        assertThatThrownBy(() -> factory.getStrategy(ProcessType.TRANSLATE))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
