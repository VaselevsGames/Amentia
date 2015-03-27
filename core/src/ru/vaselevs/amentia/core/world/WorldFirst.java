package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.player.EntityPlayer;
import ru.vaselevs.amentia.core.renderer.WorldRendererFirst;

/**
 * Created by CoreX on 23.03.2015.
 */
public class WorldFirst extends WorldBase {

    private static final int WORLD_WIDTH = 8842;
    private static final int WORLD_HEIGHT = 850;

    private WorldRendererFirst worldRenderer;

    private EntityPlayer player;

    public WorldFirst(SpriteBatch batch) {
        super(batch, WORLD_WIDTH, WORLD_HEIGHT);
        this.worldRenderer = new WorldRendererFirst(this, getBatch());
        this.player = new EntityPlayer(this);
    }

    @Override
    public void render() {
        this.worldRenderer.render();
        this.player.render();
    }

    @Override
    public void update(float deltaTime) {
        this.worldRenderer.update(deltaTime);
        this.player.update(deltaTime);
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        super.dispose();
    }
}
