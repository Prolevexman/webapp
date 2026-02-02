package prolevexman.webapp.startservicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prolevexman.webapp.dao.ProcessInstanceDao;
import prolevexman.webapp.dao.ProcessTranslateExecutionDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.model.entity.ProcessTranslateExecution;
import prolevexman.webapp.model.enums.ProcessType;
import prolevexman.webapp.service.ProcessStartService;
import prolevexman.webapp.service.factory.ProcessStrategyFactory;
import prolevexman.webapp.service.strategy.ProcessExecutionStrategy;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StartServiceTests {

    @Mock
    private ProcessStrategyFactory processStrategyFactory;

    @Mock
    private ProcessInstanceDao processInstanceDao;

    @Mock
    private ProcessTranslateExecutionDao processTranslateExecutionDao;

    @Mock
    private ProcessExecutionStrategy processExecutionStrategy;

    @InjectMocks
    private ProcessStartService processStartService;

    @Captor
    private ArgumentCaptor<ProcessInstance> processInstanceCaptor;

    @Captor
    private ArgumentCaptor<ProcessTranslateExecution> processTranslateExecutionCaptor;

    private static final String TEST_IP = "192.168.1.13";
    private static final String TEST_INPUT = "test_input";
    private static final Long GENERATED_IP = 55L;

    @BeforeEach
    void setUp() {
        doAnswer(invocationOnMock -> {
            ProcessInstance pi = invocationOnMock.getArgument(0);
            pi.setId(GENERATED_IP);
            return null;
        }).when(processInstanceDao).save(any(ProcessInstance.class));
    when(processStrategyFactory.getStrategy(any(ProcessType.class))).thenReturn(processExecutionStrategy);
    when(processExecutionStrategy.execute(anyString())).thenReturn(CompletableFuture.completedFuture("result"));
    }

    @Test
    @DisplayName("создание ProcessInstance")
    void shouldSaveProcessInstance() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        verify(processInstanceDao).save(processInstanceCaptor.capture());
        ProcessInstance saved = processInstanceCaptor.getValue();

        assertThat(saved.getInitiatorIp()).isEqualTo(TEST_IP);
        assertThat(saved.getInputData()).isEqualTo(TEST_INPUT);
        assertThat(saved.getStartTime()).isNotNull();
    }

    @Test
    @DisplayName("возвращает сгенерированный id")
    void shouldReturnGeneratedId() {
        Long id = processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        assertThat(id).isEqualTo(GENERATED_IP);
    }

    @Test
    @DisplayName("создание ProcessTranslateExecution для переводов")
    void shouldSaveProcessTranslateExecution() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.TRANSLATE);

        verify(processTranslateExecutionDao).save(processTranslateExecutionCaptor.capture());
        ProcessTranslateExecution saved = processTranslateExecutionCaptor.getValue();

        assertThat(saved.getInstanceId()).isEqualTo(GENERATED_IP);
    }

    @Test
    @DisplayName("не создает ProcessTranslateExecution для Modify")
    void shouldNotCreateProcessTranslateExecutionForModify() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        verify(processTranslateExecutionDao, never()).save(any());
    }

    @Test
    @DisplayName("должен получать стратегию по типу")
    void shouldGetStrategyByType() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        verify(processStrategyFactory).getStrategy(ProcessType.MODIFY);
    }

    @Test
    @DisplayName("должен вызывать execute() с входными данными")
    void shouldExecuteWithInputData() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        verify(processExecutionStrategy).execute(TEST_INPUT);
    }

    @Test
    @DisplayName("обновление результата при успешном выполении")
    void shouldUpdateResultOnSuccess() {
        when(processExecutionStrategy.execute(anyString())).thenReturn(CompletableFuture.completedFuture("updated result"));
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        verify(processInstanceDao, timeout(1000)).update(processInstanceCaptor.capture());
        ProcessInstance updated = processInstanceCaptor.getValue();

        assertThat(updated.getResult()).isEqualTo("updated result");

    }

    @Test
    @DisplayName("сохранение ошибки при исключении")
    void shouldSaveErrorOnException() {
        when(processExecutionStrategy.execute(anyString())).thenReturn(CompletableFuture.completedFuture("Api error"));
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        verify(processInstanceDao, timeout(1000)).update(processInstanceCaptor.capture());
        ProcessInstance updated = processInstanceCaptor.getValue();

        assertThat(updated.getResult()).contains("Api error");

    }

    @Test
    @DisplayName("порядок вызовов для Modify: сохранить -> выполнить стратегию")
    void shouldSaveBeforeExecute() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.MODIFY);

        var inOrder = inOrder(processInstanceDao, processStrategyFactory, processExecutionStrategy);
        inOrder.verify(processInstanceDao).save(any(ProcessInstance.class));
        inOrder.verify(processStrategyFactory).getStrategy(any());
        inOrder.verify(processExecutionStrategy).execute(anyString());
    }

    @Test
    @DisplayName("порядок вызовов для Translate: сохранить экземпляр -> сохранить перевод -> выполнить стратегию")
    void shouldSaveTranslateExecutionBeforExecute() {
        processStartService.startProcess(TEST_IP, TEST_INPUT, ProcessType.TRANSLATE);

        var inOrder = inOrder(processInstanceDao, processTranslateExecutionDao, processStrategyFactory, processExecutionStrategy);
        inOrder.verify(processInstanceDao).save(any(ProcessInstance.class));
        inOrder.verify(processTranslateExecutionDao).save(any(ProcessTranslateExecution.class));
        inOrder.verify(processStrategyFactory).getStrategy(any());
        inOrder.verify(processExecutionStrategy).execute(anyString());
    }
}
