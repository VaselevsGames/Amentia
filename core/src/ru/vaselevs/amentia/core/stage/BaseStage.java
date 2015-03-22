package ru.vaselevs.amentia.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by CoreX on 22.03.2015.
 */
public class BaseStage {

    private Stage stage;
    private SpriteBatch batch;
    private boolean isPaused;

    private StageManager stageManager;

    protected BaseStage( StageManager stageManager ) {
        this.stage = new Stage();
        this.batch = new SpriteBatch();
        this.updateInput();
        this.stageManager = stageManager;
    }

    protected void loadStage() {

    }

    protected void unloadStage() {
        stage.dispose();
    }

    protected void handleUpdate(float deltaTime) {

    }

    protected void handleRender() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    protected void handlePause(boolean isPaused) {
        this.isPaused = isPaused;
    }

    protected void handleResize( int width, int height ) {
        this.updateInput();
        this.getStage().getViewport().setWorldSize(width, height);
    }

    public Stage getStage() {
        return this.stage;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public StageManager getStageManager() {
        return this.stageManager;
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    private void updateInput() {
        Gdx.input.setInputProcessor(this.getStage());
    }

}
