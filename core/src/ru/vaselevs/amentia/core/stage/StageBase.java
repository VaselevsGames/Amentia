package ru.vaselevs.amentia.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by CoreX on 22.03.2015.
 */
public abstract class StageBase {

    private Stage stage;
    private SpriteBatch batch;
    private SpriteBatch batchUI;
    private boolean isPaused;

    private StageManager stageManager;

    protected StageBase(StageManager stageManager) {
        this.stage = new Stage();
        this.batch = new SpriteBatch();
        this.batchUI = new SpriteBatch();
        this.updateInput();
        this.stageManager = stageManager;
    }

    protected void loadStage() {

    }

    protected void unloadStage() {
        this.getBatch().dispose();
        this.getBatchUI().dispose();
        this.getStage().dispose();
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

    protected void handleResize(int width, int height) {
        this.updateInput();
        this.getStage().getViewport().setWorldSize(width, height);
    }

    public synchronized Stage getStage() {
        return this.stage;
    }

    public synchronized SpriteBatch getBatch() {
        return this.batch;
    }

    public synchronized SpriteBatch getBatchUI() {
        return this.batchUI;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public synchronized StageManager getStageManager() {
        return this.stageManager;
    }

    public synchronized float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void updateInput() {
        Gdx.input.setInputProcessor(this.getStage());
    }
}
