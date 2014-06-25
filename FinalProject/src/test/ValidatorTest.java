package test;



import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ziarniak.project.models.Game;

public class ValidatorTest {

	private final static int NO_ERRORS = 0;
	private Validator validator;
	private Game game;
	@Before
	public void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}

	@Test
	public void itsReturnTrueWhenGameIsCorrectlyFilled() {
		
		game.setName("StarCraft");
		game.setMaker("Blizzard");
		game.setType("Strategy");
		game.setPrice("200");
		Set<ConstraintViolation<Game>> constraintViolations = validator.validate(game);
		Assert.assertTrue(constraintViolations.size() == NO_ERRORS);
		
	
	}
	@Test
	public void itsReturnFalseWhenGameNameIsEmpty() {
		
		game.setName("");
		game.setMaker("Blizzard");
		game.setType("Strategy");
		game.setPrice("200");
		Set<ConstraintViolation<Game>> constraintViolations = validator.validate(game);
		Assert.assertFalse(constraintViolations.size() == NO_ERRORS);	
	
	}
	@Test
	public void itsReturnFalseWhenGameMakerIsEmpty() {
		
		game.setName("StarCraft");
		game.setMaker("");
		game.setType("Strategy");
		game.setPrice("200");
		Set<ConstraintViolation<Game>> constraintViolations = validator.validate(game);
		Assert.assertFalse(constraintViolations.size() == NO_ERRORS);		
	
	}
	@Test
	public void itsReturnFalseWhenGameTypeIsEmpty() {
		
		game.setName("StarCraft");
		game.setMaker("Blizzard");
		game.setType("Strategy");
		game.setPrice("200");
		Set<ConstraintViolation<Game>> constraintViolations = validator.validate(game);
		Assert.assertFalse(constraintViolations.size() == NO_ERRORS);	
	
	}
	@Test
	public void itsReturnFalseWhenGamePriceIsNotNumber() {
	
		game.setName("StarCraft");
		game.setMaker("Blizzard");
		game.setType("Strategy");
		game.setPrice("qwerty");
		Set<ConstraintViolation<Game>> constraintViolations = validator.validate(game);
		Assert.assertFalse(constraintViolations.size() == NO_ERRORS);	
	
	}
	
	

}
