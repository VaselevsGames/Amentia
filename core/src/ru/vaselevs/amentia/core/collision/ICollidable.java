package ru.vaselevs.amentia.core.collision;

import com.badlogic.gdx.math.Rectangle;
import ru.vaselevs.amentia.core.entity.EntityBase;

/**
 * Created by CoreX on 31.03.2015.
 */
public interface ICollidable {
    public Rectangle getBodyRectangle();
    public void collidedWith(EntityBase entity);
}
