package ru.vaselevs.amentia.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import ru.vaselevs.amentia.core.game.GameLoop;
import ru.vaselevs.amentia.core.stage.StageDebug;
import ru.vaselevs.amentia.core.stage.StageMainMenu;
import ru.vaselevs.amentia.core.stage.StageManager;

public class AmentiaGame implements ApplicationListener {

    private GameLoop gameLoop;
    private StageManager stageManager;

    @Override
    public void create() {
        this.stageManager = new StageManager();
        this.gameLoop = new GameLoop() {
            @Override
            public void handleRender() {
                stageManager.handleRender();
            }

            @Override
            public void handleUpdate(float deltaTime) {
                stageManager.handleUpdate(deltaTime);
            }
        };

        this.stageManager.pushStage(new StageDebug(this.stageManager));
        this.stageManager.pushStage(new StageMainMenu(this.stageManager));
        this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        this.stageManager.handleResize(width, height);
    }

    @Override
    public void render() {
        this.gameLoop.gameTick();
    }

    @Override
    public void pause() {
        this.stageManager.handlePause(true);
    }

    @Override
    public void resume() {
        this.stageManager.handlePause(false);
    }

    @Override
    public void dispose() {
        this.stageManager.dispose();
    }
}
