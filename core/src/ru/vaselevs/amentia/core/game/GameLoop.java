package ru.vaselevs.amentia.core.game;

import com.badlogic.gdx.Gdx;
import ru.vaselevs.amentia.core.game.GameConstants;

/**
 * Created by CoreX on 22.03.2015.
 */
public class GameLoop {

    private float lastTime;
    private boolean isRunning;

    public GameLoop() {
        this.lastTime = getDeltaTime();
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void gameTick() {
        handleRender();
        this.lastTime += getDeltaTime();
        while( this.lastTime >= GameConstants.UPDATE_STEP ) {
            this.lastTime -= GameConstants.UPDATE_STEP;
            handleUpdate( Math.min( this.lastTime, GameConstants.UPDATE_STEP) );
        }
    }

    public void handleUpdate( float deltaTime ) {

    }

    public void handleRender() {

    }

    public void setRuning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
