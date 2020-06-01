package hitproject.spacesheep.test;


import org.junit.jupiter.api.Test;

import hitproject.spacesheep.controller.Game;
import hitproject.spacesheep.controller.GameObject;
import hitproject.spacesheep.model.Bullet;
import hitproject.spacesheep.model.ID;
import junit.framework.TestCase;

class TestBullet extends TestCase {
	Game game = null;

	@Test
	void testTick() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to Bullet", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.Bullet) {
				assertEquals("tempObject not equal to Bullet ID", tempObject.getId(), ID.Bullet);
				Bullet bullet = (Bullet) tempObject;
				
				// Check bullet movement
				int tempX = bullet.getX();
				int tempY = bullet.getY();
				bullet.tick();
				assertEquals("Bullet X value is wrong",(int)(tempX + bullet.getVelX()),bullet.getX());
				assertEquals("Bullet Y value is wrong",(int)(tempY + bullet.getVelY()),bullet.getY());
				
				// Check Bullet object removal
				bullet.setX(1200);
				bullet.setY(400);
				bullet.tick();
				assertNull("Bullet object didn't got removed", bullet);

			}
		}

	}
}
