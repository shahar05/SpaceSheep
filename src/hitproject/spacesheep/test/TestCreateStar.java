package hitproject.spacesheep.test;

import org.junit.jupiter.api.Test;

import hitproject.spacesheep.controller.CreateStar;
import hitproject.spacesheep.controller.Game;
import hitproject.spacesheep.controller.GameObject;
import hitproject.spacesheep.model.ID;
import junit.framework.TestCase;

class TestCreateStar extends TestCase {
	Game game = null;

	@Test
	void testTick() {
		game = Game.getInstance();
		for (int i = 0; i < game.getHandler().object.size(); i++) {
			GameObject tempObject = game.getHandler().object.get(i);
			assertEquals("tempObject not equal to CreateStar", game.getHandler().object.get(i), tempObject);
			if (tempObject.getId() == ID.CreateStar) {
				assertEquals("tempObject not equal to CreateStar ID", tempObject.getId(), ID.CreateStar);
				CreateStar cs = (CreateStar) tempObject;

				// Check game score
				cs.setScoreLevel(1000);
				Game.score = 1001;
				cs.tick();
				assertEquals("Score needs to be 1400", 1400, cs.getScoreLevel());
				assertEquals(190, cs.getrEnemy());
				assertEquals(450, cs.getrShooterEnemy());
				assertEquals(1320, cs.getrHeart());
				assertEquals(925, cs.getrCrate());
				assertEquals(1425, cs.getrMissle());
				assertEquals(3, cs.getVelKamikaza());

				// Check 1 Tick
				cs.setX(200);
				assertEquals("CS need to be 200", 200, cs.getX());
				int x = cs.getX();
				cs.tick();
				assertEquals("CS value didn't change", (int) (x + cs.getVelX()), cs.getX());

				// Check 1 Tick boundary
				cs.setX(6970);
				assertEquals("CS need to be 6970", 6970, cs.getX());
				x = cs.getX();
				cs.tick();
				assertEquals("CS value needs to be 970", 970, cs.getX());

				cs.setX(-6666);
				assertEquals("CS need to be -6666", -6666, cs.getX());
				x = cs.getX();
				cs.tick();
				assertEquals("CS value needs to be 32", 32, cs.getX());

			}
		}
	}

}
