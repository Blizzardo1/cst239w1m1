package com.toasternetwork.examples;

/**
 * A Game Object
 */
public abstract class GameObject {

	/**
	 * X-Coordinate
	 */
	protected float x;

	/**
	 * Y-Coordinate
	 */
	protected float y;

	/**
	 * Width
	 */
	protected float width;

	/**
	 * Height
	 */
	protected float height;


	private final float speed = 3.15f;

	/**
	 * X-Speed
	 */
	protected float xSpeed = speed;
	/**
	 * Y-Speed
	 */
	protected float ySpeed = speed;

	/**
	 * A new instance of a Game Object
	 */
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
