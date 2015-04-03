package ru.vaselevs.amentia.core.helper;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by CoreX on 03.04.2015.
 */
public class Timer {

    public interface TimerCallback {
        void finish(Timer timer);
    }

    private float initialTime;
    private float time;

    private boolean isRunning;

    TimerCallback finishCallback;

    public Timer(float time, boolean createSuspended) {
        this.initialTime = time;
        this.time = time;
        this.isRunning = !createSuspended;
        this.finishCallback = t -> {
            throw new NotImplementedException();
        };
    }

    public void update(float deltaTime) {
        if(this.isRunning) {
            this.time -= deltaTime;
            if (this.time <= 0f) {
                this.stop();
                this.finishCallback.finish(this);
            }
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void finish(TimerCallback timerCallback) {
        this.finishCallback = timerCallback;
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
