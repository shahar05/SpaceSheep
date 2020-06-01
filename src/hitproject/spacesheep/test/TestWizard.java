package hitproject.spacesheep.test;

import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

import hitproject.spacesheep.controller.Game;
import hitproject.spacesheep.controller.GameObject;
import hitproject.spacesheep.model.ID;
import hitproject.spacesheep.view.Sheep;
import junit.framework.TestCase;

class TestWizard extends TestCase {
	Game game = null;

	@Test
	void testTick() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to Wizard", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.Player) {
				assertEquals("tempObject not equal to Player ID", tempObject.getId(), ID.Player);
				Sheep wizard = (Sheep) tempObject;
				wizard.setX(50000);
				wizard.setY(50000);
				// Check if out of boundary is being called
				wizard.tick();
				assertEquals("Wizard is out of boundary(Y = " + wizard.getY() + " )", 490, wizard.getY());
				assertEquals("Wizard is out of boundary(X = " + wizard.getX() + " )", 980, wizard.getX());

				// Check UP movement
				wizard.setY(200);
				game.getHandler().setUp(true);
				wizard.tick();
				game.getHandler().setUp(false);
				wizard.tick();
				assertEquals("Wizard Y didn't change with VelY", (int) (195 + wizard.getVelY()), wizard.getY());
				wizard.tick();
				assertEquals("Wizard moved without any VelY value", (int) (195 + wizard.getVelY()), wizard.getY());

				// Check DOWN movement
				wizard.setY(200);
				game.getHandler().setDown(true);
				wizard.tick();
				game.getHandler().setDown(false);
				wizard.tick();
				assertEquals("Wizard Y didn't change with VelY", (int) (205 + wizard.getVelY()), wizard.getY());
				wizard.tick();
				assertEquals("Wizard moved without any VelY value", (int) (205 + wizard.getVelY()), wizard.getY());

				// Check RIGHT movement
				wizard.setX(200);
				game.getHandler().setRight(true);
				wizard.tick();
				game.getHandler().setRight(false);
				wizard.tick();
				assertEquals("Wizard X didn't change with VelX", (int) (205 + wizard.getVelX()), wizard.getX());
				wizard.tick();
				assertEquals("Wizard moved without any VelX value", (int) (205 + wizard.getVelX()), wizard.getX());

				// Check LEFT movement
				wizard.setX(200);
				game.getHandler().setLeft(true);
				wizard.tick();
				game.getHandler().setLeft(false);
				wizard.tick();
				assertEquals("Wizard X didn't change with VelX", (int) (195 + wizard.getVelX()), wizard.getX());
				wizard.tick();
				assertEquals("Wizard moved without any VelX value", (int) (195 + wizard.getVelX()), wizard.getX());

				// Check SuperAmmo
				game.setSuperAmmo(-100);
				wizard.tick();
				assertEquals("SuperAmmo supposed to be 0", 0, game.getSuperAmmo());
				assertFalse("flag is true, needs to be false", wizard.getFlag());

				game.setSuperAmmo(0);
				wizard.tick();
				assertEquals("SuperAmmo supposed to be 0", 0, game.getSuperAmmo());
				assertFalse("flag is true, needs to be false", wizard.getFlag());

				game.setSuperAmmo(100);
				wizard.tick();
				assertEquals("SuperAmmo supposed to be 100", 100, game.getSuperAmmo());
				assertTrue("flag is false, needs to be true", wizard.getFlag());

			}
		}
	}

	@Test
	void testGetBounds() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to Wizard", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.Player) {
				assertEquals("tempObject not equal to Player ID", tempObject.getId(), ID.Player);
				Rectangle rec = new Rectangle(tempObject.getX(), tempObject.getY(), 32, 48);
				assertEquals(rec, tempObject.getBounds());
			}
		}
	}
}
