package hitproject.spacesheep.controller;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	public ArrayList<GameObject> object = new ArrayList<GameObject>();
	private Boolean up = false, down = false, left = false, right = false, space = false;

	public void tick() {
		if (!Game.pause) {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
				tempObject.tick();
			}
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject tempObject) {
		object.add(tempObject);

		System.out.println("The are" + object.size());
	}

	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}

	// ===== Getters And Setters=======
	public Boolean getUp() {
		if (Game.pause)
			return false;
		return up;
	}

	public void setUp(Boolean up) {
		this.up = up;
		if (Game.pause)
			this.up = false;
	}

	public Boolean getDown() {
		if (Game.pause)
			return false;
		return down;
	}

	public void setDown(Boolean down) {
		this.down = down;
		if (Game.pause)
			this.down = false;
	}

	public Boolean getLeft() {
		if (Game.pause)
			return false;
		return left;
	}

	public void setLeft(Boolean left) {
		this.left = left;
		if (Game.pause)
			this.left = false;
	}

	public Boolean getRight() {
		if (Game.pause)
			return false;
		return right;
	}

	public void setRight(Boolean right) {
		if (Game.pause)
			this.right = false;
		this.right = right;
	}

	public Boolean getSpace() {
		return space;
	}

	public void setSpace(Boolean space) {
		this.space = space;
	}
	// ===== End Getters And Setters=======
}