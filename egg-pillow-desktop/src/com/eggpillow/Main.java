package com.eggpillow;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = V.TITLE + "v" + V.VERSION;
		cfg.useGL20 = false;
		cfg.width = 576;
		cfg.height = 384;
		
		new LwjglApplication(new EggPillow(), cfg);
	}
}
