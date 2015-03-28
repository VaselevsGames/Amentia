package ru.vaselevs.amentia.core.player;

/**
 * Created by CoreX on 27.03.2015.
 */
public enum PlayerState {
    IDLE(0),
    WALK(1),
    JUMP(2),
    FALL(3),
    DEATH(4);

    int value;
    PlayerState(int value) {
        this.value = value;
    }
}
