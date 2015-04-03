package ru.vaselevs.amentia.core.helper;

/**
 * Created by CoreX on 27.03.2015.
 */
public class MathHelper {

    public static float calculateCenter(float boundFrom, float boundTo, float size) {
        return (boundFrom + boundTo) / 2 - size / 2;
    }

}
