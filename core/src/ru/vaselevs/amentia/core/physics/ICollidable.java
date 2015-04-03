package ru.vaselevs.amentia.core.physics;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.World;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 31.03.2015.
 */
public interface ICollidable {
    public Rectangle getBodyRectangle();
    public void collidedWith(EntityBase entity);
}
