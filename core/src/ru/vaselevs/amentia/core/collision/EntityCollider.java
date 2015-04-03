package ru.vaselevs.amentia.core.collision;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import ru.vaselevs.amentia.core.entity.EntityBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CoreX on 01.04.2015.
 */
public class EntityCollider<T extends EntityBase> {

    private T entity;

    /*
     * entity -> time
     */
    private Map<EntityBase, Float> collidedEntitiesMap;

    private static final float COLLIDED_ENTITY_CAPTURE_TIME = 1f;

    private ShapeRenderer shapeRenderer;

    public EntityCollider(T entity) {
        this.entity = entity;
        this.collidedEntitiesMap = new HashMap<>();
        this.shapeRenderer = new ShapeRenderer();
    }

    public void renderDebug(Camera camera) {
        this.shapeRenderer.setProjectionMatrix(camera.combined);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        Rectangle rect = this.getEntity().getBodyRectangle();
        shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        this.shapeRenderer.end();
    }

    public void update(float deltaTime) {
        // увеличиваем время хранения всех EntityBase объектов
        this.collidedEntitiesMap.entrySet().forEach(entry -> entry.setValue(entry.getValue() + deltaTime));

        // удаляем из map все объекты EntityBase, которые >= COLLIDED_ENTITY_CAPTURE_TIME
        this.collidedEntitiesMap.entrySet().removeIf(entry -> entry.getValue() >= COLLIDED_ENTITY_CAPTURE_TIME);

        this.getEntity().update(deltaTime);
    }

    public T getEntity() {
        return this.entity;
    }

    public void collidedWith(EntityCollider entityCollider) {
        if (!collidedEntitiesMap.containsKey(entityCollider.getEntity())) {
            collidedEntitiesMap.put(entityCollider.getEntity(), 0f);
            this.getEntity().collidedWith(entityCollider.getEntity());
        }
    }

}
