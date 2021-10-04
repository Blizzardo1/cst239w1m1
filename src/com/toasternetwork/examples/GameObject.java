package com.toasternetwork.examples;

public abstract class GameObject {
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	private final float speed = 3.15f;
	protected float xSpeed = speed;
	protected float ySpeed = speed;

	public GameObject() {

	}

	/**
	 * Move an object between -1 and 1 against a speed constant
	 * @param x A value between -1 and 1 that will move an object on the horizontal axis
	 * @param y A value between -1 and 1 that will move an object on the vertical axis
	 */
	public void move(float x, float y) {
		x = x > 1 ? 1 : x < -1 ? -1 : x;
		y = y > 1 ? 1 : y < -1 ? -1 : y;

		this.x += x + xSpeed;
		this.y += y + ySpeed;
	}
}
