package ru.vaselevs.amentia.core.stage;

import java.util.Stack;

/**
 * Created by CoreX on 22.03.2015.
 */
public class StageManager {
    private Stack<StageBase> stages;

    public StageManager() {
        this.stages = new Stack<>();
    }

    public synchronized void pushStage(StageBase stage) {
        this.stages.push(stage);
        StageBase currentStage = getCurrentStage();
        currentStage.loadStage();
        this.handlePause(false);
    }

    public synchronized void popStage() {
        StageBase currentStage = getCurrentStage();
        this.handlePause(true);
        currentStage.unloadStage();
        this.stages.pop();
        this.getCurrentStage().updateInput();
    }

    public synchronized void replaceCurrentStage(StageBase newStage) {
        this.popStage();
        this.pushStage(newStage);
    }

    public void handleUpdate(float deltaTime) {
        StageBase currentStage = getCurrentStage();
        currentStage.handleUpdate(deltaTime);
    }

    public void handleRender() {
        StageBase currentStage = getCurrentStage();
        currentStage.handleRender();
    }

    public synchronized void handleResize(int width, int height) {
        StageBase currentStage = getCurrentStage();
        currentStage.handleResize(width, height);
    }

    public synchronized void handlePause(boolean isPaused) {
        StageBase currentStage = getCurrentStage();
        if (currentStage.isPaused() != isPaused) {
            currentStage.handlePause(isPaused);
        }
    }

    public synchronized void dispose() {
        this.stages.forEach(stage -> stage.unloadStage());
    }

    private StageBase getCurrentStage() {
        StageBase stage = null;
        if (this.stages.size() > 0) {
            stage = this.stages.peek();
        }
        return stage;
    }

}
