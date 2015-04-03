package ru.vaselevs.amentia.core.helper;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by CoreX on 03.04.2015.
 */
public class Timer {

    public interface TimerCallback {
        void finish();
    }

    private float initialTime;
    private float time;

    private boolean isRunning;

    public Timer(float time) {
        this.initialTime = time;
        this.time = time;
        this.isRunning = true;
    }

    public void update(float deltaTime) {
        if(this.isRunning) {
            this.time -= deltaTime;
            if (this.time <= 0f) {
                this.stop();
                finish(this, () -> {
                    throw new NotImplementedException();
                });
            }
        }
    }

    public void finish(Timer timer, TimerCallback timerCallback) {
        timerCallback.finish();
    }

    public void resetTimer(float time, boolean updateInitialTime) {
        if (updateInitialTime) {
            this.initialTime = time;
        }
        this.time = time;
        this.isRunning = true;
    }

    public float getInitialTime() {
        return this.initialTime;
    }

    public void stop() {
        this.isRunning = false;
    }
}
