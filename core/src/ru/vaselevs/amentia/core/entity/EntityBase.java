package ru.vaselevs.amentia.core.entity;

import ru.vaselevs.amentia.core.resource.IDisposable;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 23.03.2015.
 */
public abstract class EntityBase implements IDisposable {
    private WorldBase world;

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected EntityBase(WorldBase world, float x, float y) {
        this.world = world;
        this.x=x;
        this.y=y;
    }

    protected WorldBase getWorld() {
        return this.world;
    }

    public void render() {
    }

    public void update(float deltaTime) {
    }

    public abstract void damage(int hit);

    @Override
    public void dispose() {

    }
}
