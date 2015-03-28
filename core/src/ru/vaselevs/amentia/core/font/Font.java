package ru.vaselevs.amentia.core.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import ru.vaselevs.amentia.core.resource.IDisposable;

public class Font implements IDisposable {

    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;

    private static final String RU_CHARSET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String EN_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBER_CHARSET = "0123456789";
    private static final String EXTRA_CHARSET = "~`!@#$%^&*()_+-=|<>?,./№;:[]'\\\"\u0020";

    public Font(String internalFont) {
        loadFont(internalFont);
    }

    private void loadFont(String internalFont) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + internalFont + ".ttf"));
        parameter = new FreeTypeFontParameter();
    }

    public BitmapFont generateFont(int size) {
        parameter.characters = RU_CHARSET + EN_CHARSET + NUMBER_CHARSET + EXTRA_CHARSET;
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        return font;
    }

    @Override
    public void dispose() {
        this.generator.dispose();
    }
}
