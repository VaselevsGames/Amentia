package ru.vaselevs.amentia.core.image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.resource.IDisposable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 04.04.2015.
 */
public class WorldBackgroundImage implements IDisposable {
    private SpriteBatch batch;

    private List<Texture> textures;

    private float offsetX;
    private int fullWidth;

    public WorldBackgroundImage(SpriteBatch batch, List<String> fileNames) {
        this.batch = batch;
        this.textures = new LinkedList<>();
        fileNames.forEach(fn -> this.textures.add(new Texture(Gdx.files.internal("images/" + fn))));
    }

    public void draw(int x, int y, int width, int height) {
        this.offsetX = 0f;
        textures.forEach(t -> {
            batch.draw(t, x + offsetX, y, t.getWidth(), t.getHeight());
            this.offsetX += t.getWidth();
        });
    }

    public int getWidth() {
        this.fullWidth = 0;
        this.textures.forEach(t -> {
            this.fullWidth += t.getWidth();
        });
        return this.fullWidth;
    }

    public int getHeight() {
        if(this.textures.size() > 0) {
            return this.textures.get(0).getHeight();
        }
        return 0;
    }

    @Override
    public void dispose() {
        this.textures.forEach(t -> t.dispose());
    }


}
