package com.eggpillow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Cliff extends Sprite implements Touchable {
	/** Width in percent of screen width. */
	private final static float WIDTH = .3f;
	/** Height in percent of screen height. */
	private final static float HEIGHT = .5f;
	/** If cliff width and height is 1, then the image size is this... */
	private final static float PADDING = 1.5f;

	/**
	 * Constructor for Cliff.
	 * 
	 * @param height
	 *            percent of height of screen where cliff top should be
	 */
	public Cliff(float height, TextureAtlas atlas) {
		super(atlas.findRegion("game_cliff"));
		setSize(PADDING * WIDTH * Gdx.graphics.getWidth(), PADDING * HEIGHT
				* Gdx.graphics.getHeight());
		setX(0);
		setY((height - HEIGHT) * Gdx.graphics.getHeight());
	}

	@Override
	public float getTopLimit(float x) {
		// TODO Complete method
		if (x < getWidth() / 2)
			return this.getHeight() - 20;
		return 0;
	}

	@Override
	public float getBottomLimit(float x) {
		return 0;
	}

	@Override
	public float getLeftLimit(float y) {
		return 0;
	}

	@Override
	public float getRightLimit(float y) {
		return this.getWidth(); // TODO Complete method
	}

	@Override
	public float getXSpeed() {
		return 0;
	}

	@Override
	public float getYSpeed() {
		return 0;
	}
}
