package ru.vaselevs.amentia.core.entity;

import ru.vaselevs.amentia.core.physics.ICollidable;
import ru.vaselevs.amentia.core.resource.IDisposable;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 23.03.2015.
 */
public abstract class EntityBase implements IDisposable, ICollidable {
    private WorldBase world;

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected String name;

    protected EntityBase(WorldBase world, float x, float y) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.name = "<unknown>";
    }

    public WorldBase getWorld() {
        return this.world;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return this.width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return this.height;
    }

    public String getName() {
        return this.name;
    }

    public abstract void render();

    public abstract void update(float deltaTime);

    public abstract void collidedWith(EntityBase entity);

    @Override
    public abstract void dispose();
}
