package ru.vaselevs.amentia.core.entity;

import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 23.03.2015.
 */
public class EntityBase {
    private WorldBase world;

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected EntityBase(WorldBase world) {
        this.world = world;
    }

    protected WorldBase getWorld() {
        return this.world;
    }

    protected void handleRender() {

    }

    protected void handleUpdate(float deltaTime) {

    }

    protected void handleInput() {

    }

}
