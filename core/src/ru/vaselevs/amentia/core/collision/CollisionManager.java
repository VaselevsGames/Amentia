package ru.vaselevs.amentia.core.collision;

import com.badlogic.gdx.graphics.Camera;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.world.WorldBase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 31.03.2015.
 */
public class CollisionManager {
    private WorldBase world;
    private List<EntityCollider> colliderObjects;

    public CollisionManager(WorldBase world) {
        this.world = world;
        this.colliderObjects = new LinkedList<>();
    }

    public void renderDebug(Camera camera) {
        this.colliderObjects.forEach(o -> o.renderDebug(camera));
    }

    public void update(float deltaTime) {
        this.checkCollisions();
        this.colliderObjects.forEach(object -> object.update(deltaTime));
    }

    private void checkCollisions() {
        this.colliderObjects.forEach(physicsObject1 -> {
            this.colliderObjects.forEach(physicsObject2 -> {
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

    private boolean isCollidedObjects(EntityCollider o1, EntityCollider o2) {
        return o1.getEntity().getBodyRectangle().overlaps(o2.getEntity().getBodyRectangle());
    }

    public WorldBase getWorld() {
        return this.world;
    }

    public boolean addEntity(EntityBase entity) {
        return this.colliderObjects.add(new EntityCollider(entity));
    }

    public boolean removeEntity(EntityBase entity) {
        return this.colliderObjects.removeIf(e -> entity == e.getEntity());
    }
}
