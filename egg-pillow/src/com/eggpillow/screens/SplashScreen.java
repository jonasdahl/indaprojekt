package com.eggpillow.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eggpillow.EggPillow;
import com.eggpillow.V;
import com.eggpillow.tween.SpriteAccessor;

/**
 * Splashscreen will show the appname and/or logo when the user starts the app.
 * 
 * @author Johan & Jonas
 * @version 2014-05-09
 */
public class SplashScreen implements Screen {
	private final static float FADE_SPEED = 1.0f;
	private final static float DELAY = 1.0f;
	// Dispose
	private Sprite splash;
	private SpriteBatch batch;

	private TweenManager tweenManager;
	private EggPillow game;

	public SplashScreen(EggPillow g) {
		game = g;
	}

	@Override
	public void render(float delta) {
		EggPillow.setBackground();
		tweenManager.update(delta);

		batch.begin();
		splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	/**
	 * Initialises splash screen and the splash animation. Starts menu screen when the animation is done.
	 */
	@Override
	public void show() {
		batch = new SpriteBatch(); // Where we're going to paint the splash
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		Texture texture = new Texture(V.SPLASH_BACKGROUND); // The texture of the splash
		splash = new Sprite(texture); // The splash is wrapped in a Sprite
		splash.setSize(V.WIDTH, V.HEIGHT);

		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, FADE_SPEED).target(1).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, FADE_SPEED).delay(DELAY).target(0).setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new MenuScreen(game));
				// TODO Chrashes somewhere here if user taps screen on splashscreen // Johan kan inte reprodusera
			}
		}).start(tweenManager);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		splash.getTexture().dispose();
	}
}