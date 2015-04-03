package ru.vaselevs.amentia.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by CoreX on 29.03.2015.
 */
public class InputManager {

    private static boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }

    private static boolean isMouseButtonPressed(int button) {
        return Gdx.input.isButtonPressed(button);
    }

    public static boolean isPressedLeft() {
        return isKeyPressed(Input.Keys.LEFT) || isKeyPressed(Input.Keys.A);
    }

    public static boolean isPressedRight() {
        return isKeyPressed(Input.Keys.RIGHT) || isKeyPressed(Input.Keys.D);
    }

    public static boolean isPressedJump() {
        return isKeyPressed(Input.Keys.SPACE);
    }

    public static boolean isPresedShift(){
        return isKeyPressed(Input.Keys.SHIFT_LEFT) || isKeyPressed(Input.Keys.SHIFT_RIGHT);
    }

    public static boolean isPressedEscape() {
        return isKeyPressed(Input.Keys.ESCAPE);
    }

    public static boolean isPressedLBM() {
        return isMouseButtonPressed(Input.Buttons.LEFT);
    }

    public static boolean isPressedRMB() {
        return isMouseButtonPressed(Input.Buttons.RIGHT);
    }

}
