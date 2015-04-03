package ru.vaselevs.amentia.core.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.vaselevs.amentia.core.animation.Animation;
import ru.vaselevs.amentia.core.animation.AnimationManager;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 03.04.2015.
 */
public class EntityShuriken extends EntityBase {
    private ResourceDisposer resourceDisposer;
    private AnimationManager animationManager;

    private int direction;

    public EntityShuriken(WorldBase world, float x, float y, int direction) {
        super(world, x, y);
        this.resourceDisposer = new ResourceDisposer();

        this.width = 90f;
        this.height = 90f;

        this.initializeAnimation();

        this.direction = direction;
    }

    private void initializeAnimation() {
        this.animationManager = new AnimationManager();
        this.resourceDisposer.addResource(this.animationManager);

        SpriteBatch batch = this.getWorld().getBatch();

        this.animationManager.add("default", new Animation("object/Attack_2x1.png", batch, 2, 0.25f, true));
    }

    @Override
    public void render() {
        this.animationManager.render(this.x, this.y, this.width, this.height, false, false);
    }

    @Override
    public void update(float deltaTime) {

        if (this.direction == 0) {
            this.direction = 1;
        }

        this.animationManager.play("default");
        this.animationManager.update(deltaTime);
        this.x += direction * 15f;
    }

    @Override
    public Rectangle getBodyRectangle() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public void collidedWith(EntityBase entity) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
    }
}
