package hitproject.spacesheep.test;

import org.junit.jupiter.api.Test;

import hitproject.spacesheep.controller.Game;
import hitproject.spacesheep.controller.GameObject;
import hitproject.spacesheep.model.Crate;
import hitproject.spacesheep.model.ID;
import junit.framework.TestCase;

class TestCrate extends TestCase {
	Game game = null;

	@Test
	void testTick() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to Crate", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.Crate) {
				assertEquals("tempObject not equal to Crate ID", tempObject.getId(), ID.Heart);
				Crate crate = (Crate) tempObject;

				// Check Crate movement
				int tempX = crate.getX();
				int tempY = crate.getY();
				crate.tick();
				assertEquals("Crate X value is wrong", tempX, crate.getX());
				assertEquals("Crate Y value is wrong", tempY + 2, crate.getY());

				// Check Crate object removal
				crate.setY(600);
				crate.tick();
				assertNull("Crate object didn't got removed", crate);
			}
		}
	}
}
