package ru.vaselevs.amentia.core.enemy;

import com.badlogic.gdx.math.Rectangle;
import ru.vaselevs.amentia.core.animation.Animation;
import ru.vaselevs.amentia.core.animation.AnimationManager;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by Brook on 28.03.2015.
 */
public class EntityEnemyHedgehog extends EntityBase {
    private ResourceDisposer resourceDisposer;

    private AnimationManager animationManager;

    private float healthPoints;

    public EntityEnemyHedgehog(WorldBase world, float x, float y) {
        super(world, x, y);
        this.width = 184;
        this.height = 150;
        this.name = "EntityEnemyHedgehog";
        this.healthPoints = 100f;

        this.resourceDisposer = new ResourceDisposer();
        this.animationManager = new AnimationManager();
        this.resourceDisposer.addResource(this.animationManager);

        this.animationManager.add("move", new Animation("enemy/Hedgehog_5x1.png", world.getBatch(), 5, 0.1f, true));
        this.animationManager.add("death", new Animation("enemy/EnemyDeath.png", world.getBatch(), 2, 0f, false));

        this.animationManager.play("move");
    }

    @Override
    public void render() {
        this.animationManager.render(this.x, this.y, this.width, this.height, false, false);
    }

    @Override
    public void update(float deltaTime) {
        this.animationManager.update(deltaTime);
        this.x += -1 * 250f * deltaTime;
    }

    private void damage(float hit) {
        this.healthPoints -= hit;
        if (this.healthPoints <= 0) {
            this.animationManager.play("death");
        }
    }

    @Override
    public Rectangle getBodyRectangle() {
        float boundsDecrease = 20f;
        return new Rectangle(
                this.x + 20f + boundsDecrease,
                this.y + boundsDecrease,
                this.width - 2 * (20f + boundsDecrease),
                this.height - 2 * boundsDecrease
        );
    }

    @Override
    public void collidedWith(EntityBase entity) {
        //System.out.println(this.name + " collided with " + entity.getName());
        //damage(30f);
    }

    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
    }
}
