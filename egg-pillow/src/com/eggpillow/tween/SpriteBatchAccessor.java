package com.eggpillow.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eggpillow.V;

public class SpriteBatchAccessor implements TweenAccessor<SpriteBatch> {
	// Defines the possible tween types
	public static final int ALPHA = 1;

	@Override
	public int getValues(SpriteBatch target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			assert false;
			return 0;
		}
	}

	@Override
	public void setValues(SpriteBatch target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			target.setColor(V.BG_R, V.BG_G, V.BG_B, newValues[0]);
			break;
		default:
			assert false;
		}
	}
}
