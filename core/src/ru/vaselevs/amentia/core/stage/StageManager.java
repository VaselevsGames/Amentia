package ru.vaselevs.amentia.core.stage;

import java.util.Stack;

/**
 * Created by CoreX on 22.03.2015.
 */
public class StageManager {
    private Stack<BaseStage> stagesList;

    public StageManager() {
        this.stagesList = new Stack<BaseStage>();
    }

    public void pushStage(BaseStage stage) {
        this.stagesList.push(stage);
        BaseStage currentStage = getCurrentStage();
        currentStage.loadStage();
        currentStage.handlePause(false);
    }

    public void popStage() {
        BaseStage currentStage = getCurrentStage();
        if(!currentStage.isPaused()) {
            currentStage.handlePause(true);
        }
        currentStage.unloadStage();
        this.stagesList.pop();
    }

    public void replaceCurrentStage(BaseStage newStage) {
        popStage();
        pushStage(newStage);
    }

    public void handleUpdate(float deltaTime) {
        BaseStage currentStage = getCurrentStage();
        currentStage.handleUpdate(deltaTime);
    }

    public void handleRender() {
        BaseStage currentStage = getCurrentStage();
        currentStage.handleRender();
    }

    public void handleResize(int width, int height) {
        BaseStage currentStage = getCurrentStage();
        currentStage.handleResize(width, height);
    }

    public void handlePause(boolean isPaused) {
        BaseStage currentStage = getCurrentStage();
        currentStage.handlePause(isPaused);
    }

    public void dispose() {
        popStage();
    }

    private BaseStage getCurrentStage() {
        BaseStage stage = null;
        if (this.stagesList.size() > 0) {
            stage = this.stagesList.peek();
        }
        return stage;
    }

}
