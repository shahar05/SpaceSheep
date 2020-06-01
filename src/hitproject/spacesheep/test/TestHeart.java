package hitproject.spacesheep.test;

import org.junit.jupiter.api.Test;

import hitproject.spacesheep.controller.Game;
import hitproject.spacesheep.controller.GameObject;
import hitproject.spacesheep.model.Heart;
import hitproject.spacesheep.model.ID;
import junit.framework.TestCase;

class TestHeart extends TestCase {
	Game game = null;

	@Test
	void testTick() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to Heart", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.Heart) {
				assertEquals("tempObject not equal to Heart ID", tempObject.getId(), ID.Heart);
				Heart heart = (Heart) tempObject;

				// Check Heart changes
				int tempY = heart.getY();
				int tempX = heart.getX();
				heart.tick();
				assertEquals("Heart value didn't change corretly", tempY + 2, heart.getY());
				assertEquals("Heart X value changed", tempX, heart.getX());

				// Check object removal
				heart.setY(500);
				heart.tick();
				assertNull("Heart object didn't got removed", heart);

			}
		}
	}
}
