package ru.vaselevs.amentia.core.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.resource.IDisposable;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 27.03.2015.
 */
public class WorldRendererBase implements IDisposable {

    private WorldBase world;
    private SpriteBatch batch;

    protected WorldRendererBase(WorldBase world, SpriteBatch batch) {
        this.world = world;
        this.batch = batch;
    }

    protected void render() {

    }

    protected void update(float deltaTime) {

    }

    protected WorldBase getWorld() {
        return this.world;
    }

    protected SpriteBatch getBatch() {
        return this.batch;
    }

    @Override
    public void dispose() {
    }
}
