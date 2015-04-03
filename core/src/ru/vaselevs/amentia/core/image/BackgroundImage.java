package ru.vaselevs.amentia.core.image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.resource.IDisposable;

/**
 * Created by CoreX on 23.03.2015.
 */
public class BackgroundImage implements IDisposable {
    private Texture texture;
    private SpriteBatch batch;

    public BackgroundImage(SpriteBatch batch, String fileName) {
        this.batch = batch;
        this.texture = new Texture(Gdx.files.internal("images/background/" + fileName));
    }

    public void draw(int x, int y, int width, int height) {
        batch.draw(this.texture, x, y, width, height);
    }

    public int getWidth() {
        return this.texture.getWidth();
    }

    public int getHeight() {
        return this.texture.getHeight();
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
