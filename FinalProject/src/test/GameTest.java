package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ziarniak.project.models.Game;

public class GameTest {

	Game game;
	@Before
	public void setUp() throws Exception {
		game=new Game("StarCraft", "Blizzard", "Strategy", "200");
	}


		@Test
		public void test() {
			Assert.notNull(game);
			
		}

	}


