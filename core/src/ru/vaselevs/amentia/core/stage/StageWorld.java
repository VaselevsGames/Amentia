package ru.vaselevs.amentia.core.stage;

import ru.vaselevs.amentia.core.world.WorldForest;

/**
 * Created by CoreX on 22.03.2015.
 */
public class StageWorld extends StageBase {
    private WorldForest worldForest;

    public StageWorld(StageManager stageManager) {
        super(stageManager);
        this.worldForest = new WorldForest(this.getStageManager(), getBatch(), getBatchUI());
    }

    @Override
    protected void handleUpdate(float deltaTime) {
        this.worldForest.update(deltaTime);
    }

    @Override
    protected void handleRender() {
        super.handleRender();
        this.getBatch().begin();
        this.worldForest.render();
        this.getBatch().end();

        this.getBatchUI().begin();
        this.worldForest.renderUI();
        this.getBatchUI().end();
    }

    @Override
    protected void unloadStage() {
        worldForest.dispose();
        super.unloadStage();
    }
}
