package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ziarniak.project.service.GameService;

public class ValidatorTest {

	@Autowired
	private GameService gameService;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		
		Assert.hasText("true");
	}

}
