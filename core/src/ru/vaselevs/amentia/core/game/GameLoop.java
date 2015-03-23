package ru.vaselevs.amentia.core.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by CoreX on 22.03.2015.
 */
public class GameLoop {

    private float lastTime;
    private boolean isRunning;

    public GameLoop() {
        this.lastTime = 0;
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void gameTick() {
        handleRender();
        this.lastTime = getDeltaTime();
        handleUpdate(this.lastTime);
    }

    public void handleUpdate(float deltaTime) {

    }

    public void handleRender() {

    }

    public void setRuning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
