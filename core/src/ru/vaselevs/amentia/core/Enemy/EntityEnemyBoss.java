package ru.vaselevs.amentia.core.Enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import ru.vaselevs.amentia.core.animation.Animation;
import ru.vaselevs.amentia.core.animation.AnimationManager;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.player.EntityShuriken;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by Brook on 04.04.2015.
 */
public class EntityEnemyBoss extends EntityBase {
    private ResourceDisposer resourceDisposer;
    private AnimationManager animationManager;

    private float originalY;
    private float accumulatedTime;
    private float healthPoints;

    public EntityEnemyBoss(WorldBase world, float x, float y) {
        super(world, x, y);
        this.resourceDisposer = new ResourceDisposer();

        this.originalY = y;

        this.width = 320f;
        this.height = 175f;
        this.healthPoints = 1000f;
        this.initializeAnimation();

        this.accumulatedTime = 0f;
    }

    private void initializeAnimation() {
        this.animationManager = new AnimationManager();
        this.resourceDisposer.addResource(this.animationManager);

        SpriteBatch batch = this.getWorld().getBatch();

        this.animationManager.add("default", new Animation("enemy/boss.png", batch, 1, 0f, false));
        this.animationManager.add("death", new Animation("enemy/EnemyDeath.png", batch, 2, 0.25f, false));

        this.animationManager.play("default");
    }

    @Override
    public void render() {
        this.animationManager.render(
                this.x,
                this.y,
                this.width,
                this.height,
                false,
                false
        );
    }

    @Override
    public void update(float deltaTime) {

        this.animationManager.update(deltaTime);

        this.accumulatedTime += deltaTime;

        if(!this.isDead()) {
            this.y = this.originalY + MathUtils.sin(this.accumulatedTime) * 50f;
        }

        if (this.isDead() && !this.animationManager.isRunning()) {
            this.getWorld().addEntityToDespawnQueue(this);
        }

    }

    @Override
    public Rectangle getBodyRectangle() {
        return new Rectangle(this.x+10f , this.y+40f, this.width-20f, this.height-50f);
    }

    private void damage(float hit) {
        this.healthPoints -= hit;
        if (this.healthPoints <= 0) {
            this.animationManager.play("death");
        }
    }

    @Override
    public void collidedWith(EntityBase entity) {
        if (entity instanceof EntityShuriken) {
            this.damage(75f);
            if(!this.isDead()) {
                this.getWorld().addEntityToDespawnQueue(entity);
            }
        }

    }

    @Override
    public boolean isDead() {
        return this.healthPoints <= 0f;
    }
    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
    }
    @Override
    public float getHealthPoints() {
        return this.healthPoints;
    }

}
