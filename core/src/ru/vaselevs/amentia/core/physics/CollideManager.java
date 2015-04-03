package ru.vaselevs.amentia.core.physics;

import com.badlogic.gdx.graphics.Camera;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.world.WorldBase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 31.03.2015.
 */
public class CollideManager {
    private WorldBase world;
    private List<Collider> worldPhysicalObjects;

    public CollideManager(WorldBase world) {
        this.world = world;
        this.worldPhysicalObjects = new LinkedList<>();
    }

    public void renderDebug(Camera camera) {
        this.worldPhysicalObjects.forEach(o -> o.renderDebug(camera));
    }

    public void update(float deltaTime) {
        this.checkCollisions();
        this.worldPhysicalObjects.forEach(object -> object.update(deltaTime));
    }

    private void checkCollisions() {
        this.worldPhysicalObjects.forEach(physicsObject1 -> {
            this.worldPhysicalObjects.forEach(physicsObject2 -> {
                if (physicsObject1 != physicsObject2) {
                    boolean isCollided = this.isCollidedObjects(physicsObject1, physicsObject2);
                    if (isCollided) {
                        physicsObject1.collidedWith(physicsObject2);
                        physicsObject2.collidedWith(physicsObject1);
                    }
                }
            });
        });
    }

    private boolean isCollidedObjects(Collider o1, Collider o2) {
        return o1.getEntity().getBodyRectangle().overlaps(o2.getEntity().getBodyRectangle());
    }

    public WorldBase getWorld() {
        return this.world;
    }

    public boolean addEntity(EntityBase entity) {
        return this.worldPhysicalObjects.add(new Collider(entity));
    }

    public boolean removeEntity(EntityBase entity) {
        return this.worldPhysicalObjects.removeIf(e -> entity == e.getEntity());
    }
}
