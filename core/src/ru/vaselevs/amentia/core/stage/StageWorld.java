package ru.vaselevs.amentia.core.stage;

import ru.vaselevs.amentia.core.world.WorldFirst;

/**
 * Created by CoreX on 22.03.2015.
 */
public class StageWorld extends StageBase {
    private WorldFirst worldFirst;

    public StageWorld(StageManager stageManager) {
        super(stageManager);
        this.worldFirst = new WorldFirst(getBatch());
    }


    @Override
    protected void handleUpdate(float deltaTime) {
        this.worldFirst.update(deltaTime);
    }

    @Override
    protected void handleRender() {
        super.handleRender();
        this.getBatch().begin();
        this.worldFirst.render();
        this.getBatch().end();
    }

    @Override
    protected void unloadStage() {
        worldFirst.dispose();
        super.unloadStage();
    }
}
