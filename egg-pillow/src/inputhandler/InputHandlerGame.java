package inputhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.eggpillow.EggPillow;
import com.eggpillow.Pillow;

public class InputHandlerGame implements InputProcessor {
	
	private Pillow pillow;
	private boolean onPillow = false;
	EggPillow game;
	
	public InputHandlerGame(Pillow p, EggPillow g) {
		pillow = p;
		game = g;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.MENU) {
			game.gameScreen.pauseGame();
		}else if (keycode == Keys.BACK) {
			if (game.gameScreen.isPaused()) {
				game.gameScreen.dispose();
				game.setScreen(game.menuScreen);
			}
			game.gameScreen.pauseGame();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (pillow.inside(screenX, screenY)) {
			pillow.setX(screenX);
			pillow.setY(Gdx.graphics.getHeight() - screenY);
			onPillow = true;
		}
		if (game.gameScreen.isPaused() ){
			game.gameScreen.unPauseGame();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		onPillow = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (onPillow || pillow.inside(screenX, screenY)) {
			pillow.setX(screenX);
			pillow.setY(Gdx.graphics.getHeight() - screenY);
		}
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
