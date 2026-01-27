package prolevexman.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import prolevexman.webapp.dao.inmemory.InMemoryProcessInstanceDao;
import prolevexman.webapp.model.entity.ProcessInstance;
import prolevexman.webapp.service.strategy.ModifyProcess;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
class WebappApplicationTests {

	@Test
	void testModifyProcess() {
		InMemoryProcessInstanceDao dao = new InMemoryProcessInstanceDao();
		ModifyProcess process = new ModifyProcess(dao);
		ProcessInstance instance = new ProcessInstance("1.1.1.1", "Привет мир");
		String input = instance.getInputData();
		String expected = "рйм тжвйрП";
		process.execute(instance);
		System.out.println(instance.getResult());
		assertEquals(expected, instance.getResult());
	}

}
