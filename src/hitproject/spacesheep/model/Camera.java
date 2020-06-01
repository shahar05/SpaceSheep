package hitproject.spacesheep.model;

import hitproject.spacesheep.controller.*;

public class Camera {
	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject object) {
	}

	// ======== Getters And Setters
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
