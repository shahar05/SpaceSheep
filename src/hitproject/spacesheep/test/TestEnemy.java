package hitproject.spacesheep.test;

import org.junit.jupiter.api.Test;

import hitproject.spacesheep.controller.Game;
import hitproject.spacesheep.controller.GameObject;
import hitproject.spacesheep.model.Enemy;
import hitproject.spacesheep.model.ID;
import junit.framework.TestCase;

class TestEnemy extends TestCase {
	public Game game = null;

	@Test
	void testTick() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to Enemy", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.Enemy) {
				assertEquals("tempObject not equal to Enemy ID", tempObject.getId(), ID.Enemy);
				Enemy enemy = (Enemy) tempObject;

				// Check Enemy movement
				int tempX = enemy.getX();
				int tempY = enemy.getY();
				enemy.tick();
				assertEquals("Enemy X value is wrong", tempX, enemy.getX());
				assertEquals("Enemy Y value is wrong", tempY + enemy.getVelocity(), enemy.getY());

				// Check Enemy object removal
				enemy.setY(400);
				enemy.setHp(40);
				enemy.tick();
				assertNotNull("Enemy object  got removed", enemy);
				/*
				enemy.setY(400);
				enemy.setHp(0);
				enemy.tick();
				assertNull("Enemy object didn't got removed", enemy);
				*/
			}
		}
	}
}
