package com.eggpillow.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.eggpillow.V;

/**
 * A pillow representation
 * @author jonas
 * @version 2014-05-09
 */
public class Pillow extends Touchable {
	private float level;
	private boolean locked;
	private ArrayList<Touchable> touchables;

	private float[] oldX, oldY;
	private int nextOld = 0;
	private float mX, mY;

	/**
	 * Constructor for Pillow.
	 * 
	 * @param limitXLeft the pillow can't be taken left of this column
	 * @param limitXRight the pillow can't be taken right of this column
	 * @param yLevel the level of the pillow in percent of the screen. if set to
	 *               negative value, then the pillow has no fixed level y-wise.
	 * @param width the width (in percent of screen width)
	 * @param height the height (in percent of screen height)
	 */
	public Pillow(ArrayList<Touchable> thouchables, float yLevel, TextureAtlas atlas) {
		super(atlas.findRegion(V.PILLOW_REGION), SQUARE);
		setSize(V.WIDTH * V.PILLOW_WIDTH, V.HEIGHT * V.PILLOW_HEIGHT);
		if (yLevel < 0) {
			locked = false;
		} else {
			locked = true;
			level = yLevel;
		}
		this.touchables = thouchables;
		setX(0);
		setY(-1 * level);
		oldX = new float[3];
		oldY = new float[3];
		softnessX = 0;
		softnessY = 0;
	}

	/**
	 * Update the pillows properties. speed
	 * 
	 * @param delta
	 *            Time since last update (seconds)
	 */
	public void update(float delta) {
		// TODO fix bug and vertical
		setX(mX);
		setY(mY);
		updateSpeed(delta);
		// CollisionDetection
		for (Touchable touch : touchables) {
			if (touch != this) {
				ReturnClass intersect = intersects(touch);
				if (intersect.t != null) {
					if (45 <= intersect.v && intersect.v <= 135) {
						setY(touch.getY() - getHeight());
					} else if (225 <= intersect.v && intersect.v <= 315) {
						setY(touch.getY() + touch.getHeight());
					}
					if (intersect.v == 360 || intersect.v == 315 || intersect.v == 45) {
						setX(touch.getX() - getWidth());
					} else if (135 <= intersect.v && intersect.v <= 225) {
						setX(touch.getX() + touch.getWidth());
					}
				}
			}
		}
	}

	/**
	 * Update the speed of the pillow.
	 * 
	 * @param delta
	 *            Time since last update seconds
	 */
	private void updateSpeed(float delta) {
		// TODO delay or fluctuate
		xSpeed = (getX() - medel(oldX)) * delta * V.WIDTH;
		ySpeed = (getY() - medel(oldY)) * delta * V.HEIGHT;
		// xSpeed = (getX() - oldX[ nextOld]) * delta * V.WIDTH;
		// ySpeed = (getY() - oldY[nextOld]) * delta * V.HEIGHT;

		oldX[nextOld] = getX();
		oldY[nextOld] = getY();
		nextOld++;
		if (nextOld == 3) {
			nextOld = 0;
		}
	}

	private float medel(float[] v) {
		float x = 0;
		for (float f : v) {
			x += f;
		}
		return x / v.length;
	}

	/**
	 * Set the x position. This will put the pillow on the screen if x is out of
	 * bounds.
	 */
	@Override
	public void setX(float x) {
		if (x > V.WIDTH - getWidth())
			return;
		// x = V.WIDTH - getWidth();
		if (x < 0)
			return;
		// x = 0;
		super.setX(x);
	}

	/**
	 * Set the y position. This will put the pillow on a accepted position if y
	 * is illegal.
	 */
	@Override
	public void setY(float y) {
		if (y < 0) {
			y = 0;
			return;
		} else if (y > V.HEIGHT - getHeight()) {
			y = V.HEIGHT - getHeight();
			return;
		}

		if (!locked)
			super.setY(y);
		else
			super.setY(V.HEIGHT * level);
	}

	/**
	 * Checks if testX and testY is inside or very close to the pillow.
	 * 
	 * @return true if testX and textY is close to the pillow
	 */
	public boolean inside(int testX, int testY, int paddingX, int paddingY) {
		if (testX > getX() - paddingX && testX < getWidth() + getX() + paddingX && testY < V.HEIGHT - getY() + paddingY
				&& testY > V.HEIGHT - getY() - getHeight() - paddingY) {
			return true;
		}
		return false;
	}

	public void setMouseX(float x) {
		x -= getWidth()/2;
		if (x > V.WIDTH - getWidth()) {
			x = V.WIDTH - getWidth();
			return;
		}
		if (x <= 0) {
			x = 0;
			return;
		}
		mX = x;
	}
	
	/**
	 * Set mouseY if y 
	 * @param y
	 */
	public void setMouseY(float y) {
		y -= getHeight()/2;
		if (y > V.HEIGHT - getHeight()) {
			y = V.HEIGHT - getHeight();
			return;
		}
		if (y <= 0) {
			y = 0;
			return;
		}
		mY = y;
	}

}
