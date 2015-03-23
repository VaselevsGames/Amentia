package ru.vaselevs.amentia.core.stage;

import java.util.Stack;

/**
 * Created by CoreX on 22.03.2015.
 */
public class StageManager {
    private Stack<StageBase> stages;

    public StageManager() {
        this.stages = new Stack<StageBase>();
    }

    public void pushStage(StageBase stage) {
        this.stages.push(stage);
        StageBase currentStage = getCurrentStage();
        currentStage.loadStage();
        this.handlePause(false);
    }

    public void popStage() {
        StageBase currentStage = getCurrentStage();
        this.handlePause(true);
        currentStage.unloadStage();
        this.stages.pop();
    }

    public void replaceCurrentStage(StageBase newStage) {
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

    public void handleResize(int width, int height) {
        StageBase currentStage = getCurrentStage();
        currentStage.handleResize(width, height);
    }

    public void handlePause(boolean isPaused) {
        StageBase currentStage = getCurrentStage();
        boolean currentState = currentStage.isPaused();
        if (currentState != isPaused) {
            currentStage.handlePause(isPaused);
        }
    }

    public void dispose() {
        this.popStage();
    }

    private StageBase getCurrentStage() {
        StageBase stage = null;
        if (this.stages.size() > 0) {
            stage = this.stages.peek();
        }
        return stage;
    }

}
