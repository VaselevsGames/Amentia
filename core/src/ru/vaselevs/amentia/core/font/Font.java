package ru.vaselevs.amentia.core.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font {

    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;

    private static final String ru_charset = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String en_charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String number_charset = "0123456789";
    private static final String extra_charset = "~`!@#$%^&*()_+-=|<>?,./№;:[]'\\\"\u0020";


    public BitmapFont generateFont(int size) {
        parameter.characters = ru_charset + en_charset + number_charset + extra_charset;
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        return font;
    }

    public void dispose() {
        this.generator.dispose();
    }

    private void loadFont(String internalFont) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + internalFont + ".ttf"));
        parameter = new FreeTypeFontParameter();
    }

    public Font(String internalFont) {
        loadFont(internalFont);
    }

}
