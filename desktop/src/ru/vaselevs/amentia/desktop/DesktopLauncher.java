package ru.vaselevs.amentia.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.vaselevs.amentia.core.AmentiaGame;
import ru.vaselevs.amentia.core.game.GameConstants;

public class DesktopLauncher {
    public static void main (String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.resizable = false;
        config.width = 1200;
        config.height = 850;
        config.title = GameConstants.GAME_TITLE;
        new LwjglApplication(new AmentiaGame(), config);
    }
}
